package conf;


import controllers.*;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {

        router.GET().route("/admin/login").globalFilters().with(LoginController::index);
        router.POST().route("/admin/login").globalFilters().with(LoginController::VerifyLogin);
        router.GET().route("/admin/logout").globalFilters().with(LoginController::Logout);

        router.GET().route("/quiz").globalFilters().with(QuizController::index);
        router.GET().route("/").globalFilters().with(QuizController::index);


        router.GET().route("/admin/home").with(HomeController::index);

        router.GET().route("/admin/participants").with(ParticipantsController::index);
        router.GET().route("/admin/participants/reset").with(ParticipantsController::resetParticipants);
        router.POST().route("/admin/participants/delete-all").with(ParticipantsController::deleteAllParticipants);

        router.GET().route("/admin/questions").with(QuestionsController::index);
        router.GET().route("/admin/questions/create").with(QuestionsController::createForm);
        router.POST().route("/admin/questions/create").with(QuestionsController::create);
        router.GET().route("/admin/questions/edit/{id}").with(QuestionsController::edit);
        router.POST().route("/admin/questions/edit").with(QuestionsController::saveEdit);
        router.GET().route("/admin/questions/delete/{id}").with(QuestionsController::delete);



        //router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").globalFilters().with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").globalFilters().with(AssetsController::serveStatic);

        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").globalFilters().with(QuizController::index);
    }

}
