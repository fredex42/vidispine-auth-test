package com.vidispine.security.auth.spi;

import org.apache.shiro.authc.AuthenticationInfo;

public interface VidispineUserInfoProvider {
    /**
     * Returns the user information for the user that is performing the request,
     * or null if this is not an external user.
     */
    VidispineUserInfo getUserInfo(AuthenticationInfo info);
}
