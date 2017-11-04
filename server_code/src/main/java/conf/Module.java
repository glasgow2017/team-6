package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import services.DBService;
import services.GEOIPService;
import services.LoginService;
import services.ParticipantsService;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        bind(DBService.class);
        bind(LoginService.class);
        bind(GEOIPService.class);
        bind(ParticipantsService.class);
    }

}
