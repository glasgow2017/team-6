package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.ParticipantsService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QuizController {

    @Inject
    ParticipantsService participantsService;

    private long GetParticipantID(Context context, Session session)
    {
        String ip = context.getRemoteAddr();
        Long pId = participantsService.VerifyParticipantId(session.get("participant_id"), ip);
        session.put("participant_id", pId.toString());
        return pId;
    }

    public Result index(Context context, Session session)
    {
        long pId = GetParticipantID(context, session);
        return Results.html();
    }
}
