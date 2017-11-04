package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.zaxxer.hikari.HikariDataSource;
import ninja.utils.NinjaProperties;

/**
 * Created by Trent on 5/6/2017.
 */

@Singleton
public class DBService
{
    private HikariDataSource ds = new HikariDataSource();

    @Inject
    public DBService(@Named("application.db.jdbc") String jdbcString,
                     @Named("application.db.username") String dbUsername,
                     @Named("application.db.password") String dbPassword)
    {
        ds.setJdbcUrl(jdbcString);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        ds.setDriverClassName("com.mysql.jdbc.Driver");
    }

    public HikariDataSource GetDataSource()
    {
        return this.ds;
    }
}
