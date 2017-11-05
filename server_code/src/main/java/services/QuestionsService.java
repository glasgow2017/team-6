package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import generated.db.tables.records.QuizParticipantsRecord;
import generated.db.tables.records.QuizQuestionsRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static generated.db.Tables.QUIZ_PARTICIPANTS;
import static generated.db.tables.QuizQuestions.QUIZ_QUESTIONS;

@Singleton
public class QuestionsService {

    final static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    DBService dbService;

    public int CreateQuestion(String questionText,
                              String ans1,
                              String ans2,
                              String ans3,
                              String ans4,
                              String correctAnswer)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);

        QuizQuestionsRecord record = create.newRecord(QUIZ_QUESTIONS);

        record.setQuestionText(questionText);
        record.setAns1(ans1);
        record.setAns2(ans2);
        record.setAns3(ans3);
        record.setAns4(ans4);
        record.setQuestionCountry("UK");
        record.setCorrectAnswer(Integer.parseInt(correctAnswer));
        record.store();

        return record.getId();
    }

    public void UpdateQuestion(int questionId,
                               String questionText,
                               String ans1,
                               String ans2,
                               String ans3,
                               String ans4,
                               int correctAnswer)
    {
        QuizQuestionsRecord record = this.GetQuestion(questionId);

        record.setQuestionText(questionText);
        record.setAns1(ans1);
        record.setAns2(ans2);
        record.setAns3(ans3);
        record.setAns4(ans4);
        record.setCorrectAnswer(correctAnswer);
        record.store();
    }

    public List<QuizQuestionsRecord> GetAllQuizQuestions()
    {
        LinkedList<QuizQuestionsRecord> list = new LinkedList<QuizQuestionsRecord>();
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);

        Result<QuizQuestionsRecord> results =  create
                .selectFrom(QUIZ_QUESTIONS)
                .orderBy(QUIZ_QUESTIONS.ID.asc())
                .fetch();

        for (QuizQuestionsRecord record : results)
        {
            list.add(record);
        }

        return list;
    }

    public void DeleteQuestion(int questionId)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        create.deleteFrom(QUIZ_QUESTIONS).where(QUIZ_QUESTIONS.ID.eq(questionId)).execute();
    }

    public QuizQuestionsRecord GetQuestion(int questionId)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        return create.selectFrom(QUIZ_QUESTIONS).where(QUIZ_QUESTIONS.ID.eq(questionId)).fetchOne();
    }
}
