package controllers;

import generated.db.tables.records.QuizQuestionsRecord;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.ParticipantsService;
import services.QuestionsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class QuizController {

    @Inject
    ParticipantsService participantsService;

    @Inject
    QuestionsService questionsService;

    private long GetParticipantID(Context context, Session session)
    {
        String ip = context.getRemoteAddr();
        Long pId = participantsService.VerifyParticipantId(session.get("participant_id"), ip);
        session.put("participant_id", pId.toString());
        return pId;
    }

    public Result index(Context context, Session session)
    {
        Result result = Results.html();

        List<QuizQuestionsRecord> questionsList = questionsService.GetAllQuizQuestions();
        result.render("questionsList", questionsList);

        List<Integer> agesList = new LinkedList<Integer>();
        for (int i = 14; i < 100; i++) { agesList.add(i); }
        result.render("agesList", agesList);

        return result;
    }

    public Result redirectToIndex()
    {
        return Results.redirect("/");
    }

    public Result QuizSubmit(Context context, Session session)
    {
        Result result = Results.html().template("/views/QuizController/quiz-result.ftl.html");
        long pId = GetParticipantID(context, session);
        List<QuizQuestionsRecord> questionsList = questionsService.GetAllQuizQuestions();

        int numQuestions = questionsList.size();
        int numCorrect = 0;
        for (QuizQuestionsRecord question : questionsList)
        {
            String radioId = "q" + question.getId() + "-radio";
            String correctAnswer = "q" + question.getId() + "-ans" + question.getCorrectAnswer();

            String userAnswer = context.getParameter(radioId, "");
            if (userAnswer.equals(correctAnswer))
            {
                numCorrect++;
            }
        }

        String ip = context.getRemoteAddr();
        int percentCorrect = (int)(((double)numCorrect / (double)numQuestions) * 100);
        String country = context.getParameter("country", "UK");
        Integer age = Integer.parseInt(context.getParameter("age", "0"));
        String sex = context.getParameter("gender", "O");

        result.render("numQuestions", numQuestions);
        result.render("numCorrect", numCorrect);

        participantsService.UpdateParticipant(pId, age, sex, ip, country, percentCorrect);

        return result;
    }
}
