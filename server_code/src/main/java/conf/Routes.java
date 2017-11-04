
package conf;


import controllers.*;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {

        router.GET().route("/login").globalFilters().with(LoginController::index);
        router.POST().route("/login").globalFilters().with(LoginController::VerifyLogin);
        router.GET().route("/logout").globalFilters().with(LoginController::Logout);

        router.GET().route("/").with(HomeController::index);
        router.GET().route("/home").with(HomeController::index);

        router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").globalFilters().with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").globalFilters().with(AssetsController::serveStatic);

        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").globalFilters().with(HomeController::index);
    }

}
