package controllers;

import controllers.forms.QuestionTextForm;
import ninja.Result;
import ninja.Results;
import services.QuestionsService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class QuestionsController {

    @Inject
    QuestionsService questionsService;

    public Result index()
    {
        return Results.html();
    }

    public Result createForm() {
        return Results.html().template("/views/QuestionsController/create-question.ftl.html");
    }

    public Result create(QuestionTextForm form) {
        int questionId = questionsService.CreateQuestion(form.questionText);
        return Results.redirect("/admin/questions/edit/" + questionId);
    }
}
