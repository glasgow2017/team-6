package services;

import java.util.Base64;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import generated.db.Tables.*;
import generated.db.tables.pojos.Users;
import generated.db.tables.records.UsersRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static generated.db.Tables.USERS;

@Singleton
public class LoginService {

    final static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    DBService dbService;

    private static final int PBKDF_ITERATIONS = 100;

    private String GetSalt(String username)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        UsersRecord result = create.fetchOne(USERS, USERS.NAME.eq(username));
        if (result == null) return null;
        return result.getSalt();
    }

    private int GetUserId(String username, String passwordHash)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        UsersRecord result = create.fetchOne(USERS, USERS.NAME.eq(username).and(USERS.PASSWORD_HASH.eq(passwordHash)));
        if (result == null) return -1;
        return result.getId();
    }

    public int VerifyLogin(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // Find salt using username
        String salt = GetSalt(username);
        if (salt == null)
        {
            return -1;
        }
        // Derive hash from salt
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), PBKDF_ITERATIONS, 64 * 8);
        byte[] hashBytes = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec).getEncoded();
        String hash = new String(Base64.getEncoder().encode(hashBytes));
        // Find user id using username and password hash
        return GetUserId(username, hash);
    }

}
