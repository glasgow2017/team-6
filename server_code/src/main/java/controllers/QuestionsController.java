package controllers;

import controllers.forms.QuestionEditForm;
import controllers.forms.QuestionTextForm;
import generated.db.tables.records.QuizQuestionsRecord;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.FlashScope;
import services.QuestionsService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class QuestionsController {

    @Inject
    QuestionsService questionsService;

    public Result index()
    {
        Result result = Results.html();
        List<QuizQuestionsRecord> questionsList = questionsService.GetAllQuizQuestions();
        result.render("questionsList", questionsList);
        return result;
    }

    public Result createForm() {
        return Results.html().template("/views/QuestionsController/create-question.ftl.html");
    }

    public Result create(QuestionTextForm form, FlashScope flashScope) {
        questionsService.CreateQuestion(form.questionText, form.ans1, form.ans2, form.ans3,
                form.ans4, form.correctAnswer);
        flashScope.success("Successfully created new question");
        return Results.redirect("/admin/questions");
    }

    public Result edit(@PathParam("id") String questionId)
    {
        Result result = Results.html().template("/views/QuestionsController/edit-question.ftl.html");
        QuizQuestionsRecord question = questionsService.GetQuestion(Integer.parseInt(questionId));
        result.render("question", question);
        return result;
    }

    public Result saveEdit(QuestionEditForm questionEditForm, FlashScope flashScope)
    {
        questionsService.UpdateQuestion(Integer.parseInt(questionEditForm.questionId), questionEditForm.questionText,
                questionEditForm.ans1, questionEditForm.ans2, questionEditForm.ans3, questionEditForm.ans4,
                Integer.parseInt(questionEditForm.correctAnswer));

        flashScope.success("Successfully edited question");
        return Results.redirect("/admin/questions");
    }

    public Result delete(@PathParam("id") String questionId, FlashScope flashScope)
    {
        questionsService.DeleteQuestion(Integer.parseInt(questionId));
        flashScope.success("Successfully deleted question");
        return Results.redirect("/admin/questions");
    }
}
