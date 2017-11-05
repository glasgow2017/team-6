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

        GetParticipantID(context, session);

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
}
