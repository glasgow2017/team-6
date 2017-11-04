package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import generated.db.tables.records.QuizQuestionsRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static generated.db.tables.QuizQuestions.QUIZ_QUESTIONS;

@Singleton
public class QuestionsService {

    final static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    DBService dbService;

    public int CreateQuestion(String questionText)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);

        QuizQuestionsRecord record = create.newRecord(QUIZ_QUESTIONS);

        record.setQuestionText(questionText);
        record.setCorrectAnswerId(0);
        record.setQuestionCountry("UK");
        record.store();

        return record.getId();
    }
}
