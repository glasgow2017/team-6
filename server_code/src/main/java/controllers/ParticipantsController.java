package controllers;

import generated.db.tables.records.QuizParticipantsRecord;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.FlashScope;
import ninja.session.Session;
import services.ParticipantsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ParticipantsController {

    @Inject
    ParticipantsService participantsService;


    public Result index(Context context, Session session)
    {
        Result result = Results.html();

        long numParticipants = participantsService.GetNumParticipants();
        result.render("numParticipants", numParticipants);

        result.render("numCountries", participantsService.GetNumCountries());
        result.render("percentCorrect", participantsService.GetPercentCorrectAnswers());
        result.render("averageAge", participantsService.GetAverageAge());

        long numMen = participantsService.GetNumMen();
        long numWomen = participantsService.GetNumWomen();

        int percentMen = (int)(((double)numMen / (double)numParticipants) * 100);
        result.render("percentMen", percentMen);

        int percentWomen = (int)(((double)numWomen / (double)numParticipants) * 100);
        result.render("percentWomen", percentWomen);

        int percentOther = 100 - (percentMen + percentWomen);
        result.render("percentOther", percentOther);

        List<QuizParticipantsRecord> participantsList = participantsService.GetLatestQuizParticipants();
        result.render("participantsList", participantsList);

        return result;
    }

    public Result resetParticipants()
    {
        return Results.html().template("/views/ParticipantsController/reset-participants.ftl.html");
    }

    public Result deleteAllParticipants(FlashScope flashScope)
    {
        participantsService.DeleteAllParticipants();
        flashScope.success("Deleted all participant data.");
        return Results.redirect("/admin/participants");
    }

}
