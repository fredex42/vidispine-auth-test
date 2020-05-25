package com.gu;

import com.vidispine.security.auth.spi.VidispineUserInfo;

import java.util.List;
import java.util.Map;

public class JwtUser implements VidispineUserInfo {
    private final String username;
    private final String realname;

    public JwtUser(String username, String forename, String given_name) {
        this.username = username;
        this.realname = forename + " " + given_name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getRealName() {
        return realname;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }

    @Override
    public List<String> getGroups() {
        return List.of();
    }
}
