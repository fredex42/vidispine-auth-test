package com.gu;

import com.vidispine.security.auth.spi.VidispineUserInfo;

import java.util.List;
import java.util.Map;

public class JwtUser implements VidispineUserInfo {
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getRealName() {
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }

    @Override
    public List<String> getGroups() {
        return null;
    }
}
