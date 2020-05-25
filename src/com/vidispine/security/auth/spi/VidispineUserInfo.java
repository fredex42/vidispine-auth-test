package com.vidispine.security.auth.spi;

import java.util.List;
import java.util.Map;

public interface VidispineUserInfo {
    /**
     * Returns the username;
     */
    String getUsername();

    /**
     * Returns the real name of the user.
     */
    String getRealName();

    /**
     * Returns the metadata that exists for the user.
     */
    Map<String, String> getMetadata();

    /**
     * Returns the names of the groups that the user is part of.
     */
    List<String> getGroups();
}
