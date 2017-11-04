package services;

import javax.inject.Singleton;

@Singleton
public class GEOIPService {

    public String GetCountryOfIP(String ip)
    {
        return "UK";
    }
}
