package com.gu;
import com.vidispine.security.auth.spi.VidispineUserInfo;
import com.vidispine.security.auth.spi.VidispineUserInfoProvider;
import org.apache.shiro.authc.AuthenticationInfo;


public class JwtUserCreator implements VidispineUserInfoProvider {
    //private final Logger logger = VidiLoggerFactory.getLogger(getClass());

    @Override
    public VidispineUserInfo getUserInfo(AuthenticationInfo info) {
        Object credentials = info.getCredentials();
        //logger.info("JwtUserCreator: shiro provided credentials of type " + credentials.getClass().getCanonicalName());
        String realmNames = info.getPrincipals().getRealmNames().toString();
        //logger.info("JwtUserCreator: shiro returned realms " + realmNames);
        return null;
    }
}
