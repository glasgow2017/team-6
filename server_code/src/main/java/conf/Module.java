
package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import services.DBService;
import services.LoginService;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        bind(DBService.class);
        bind(LoginService.class);
    }

}
