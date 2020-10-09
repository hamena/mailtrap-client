/*
Simple Java wrapper for Mailtrap API Rest
Copyright (C) 2014 jxc876

This file is part of Mailtrap Java Client.

Mailtrap Java Client is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or
(at your option) any later version.

Mailtrap Java Client is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Mailtrap Java Client.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.java7notes.mailtrap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hamena
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int id;
    
    private String name;
    
    private String email;
    
    @JsonProperty("api_token")
    private String apiToken;
    
    @JsonProperty("gravatar_img")
    private String gravatarImg;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    private String timezone;
    
    @JsonProperty("timezone_in_int")
    private int timezoneInInt;
    
    @JsonProperty("pending_email")
    private String pendingEmail;

    public User() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getGravatarImg() {
        return gravatarImg;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getTimeZoneInt() {
        return timezoneInInt;
    }

    public boolean getPendingEmail() {
        return pendingEmail==null ? false : Boolean.valueOf(pendingEmail);
    }
}
