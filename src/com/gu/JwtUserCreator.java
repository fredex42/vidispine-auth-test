package com.gu;
import com.vidispine.logging.Logger;
import com.vidispine.logging.VidiLoggerFactory;
import com.vidispine.security.auth.spi.VidispineUserInfo;
import com.vidispine.security.auth.spi.VidispineUserInfoProvider;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;


public class JwtUserCreator implements VidispineUserInfoProvider {
    private final Logger logger = VidiLoggerFactory.getLogger(getClass());

    @Override
    public VidispineUserInfo getUserInfo(AuthenticationInfo info) {
        Object credentials = info.getCredentials();
        logger.info("JwtUserCreator: shiro provided credentials of type " + credentials.getClass().getCanonicalName());
        String credentialsString = new String((char[]) credentials);

        logger.info("JwtUserCreator: shiro provided credentials: " + credentialsString);

        PrincipalCollection principals = info.getPrincipals();
        if(principals!=null) {
            Set<String> realms = info.getPrincipals().getRealmNames();
            if(realms!=null) {
                logger.info("JwtUserCreator: shiro returned realms " + realms);
            } else {
                logger.warn("JwtUserCreator: shiro returned principals but no realm names!");
            }
        } else {
            logger.warn("JwtUserCreated: shiro returned no principals!");
        }
        return null;
    }
}
