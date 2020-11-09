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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author hamena
 */
public class MailtrapAPI {
    
    private final String baseUrl = "https://mailtrap.io/";
    private final String userUrl = "api/v1/user";
	private final String inboxUrl = "api/v1/inboxes/{inbox_id}"; 
    private final String messagesUrl = "api/v1/inboxes/{inbox_id}/messages";
    private final String messageUrl = "api/v1/inboxes/{inbox_id}/messages/{message_id}";
    
    private String apiToken;

    private Client client = ClientBuilder.newClient();

    public MailtrapAPI(String apiToken) {
        this.apiToken = apiToken;
    }

	public Builder configure(String url){
        return client.target(url).queryParam("api_token", apiToken).request(MediaType.APPLICATION_JSON);
    }

    public String getUserUrl() {
        return baseUrl + userUrl;
    }
    
    public String getInboxUrl(String inboxId) {
        return baseUrl + inboxUrl.replace("{inbox_id}", inboxId);
    }

    public String getMessagesUrl(String inboxId) {
        return baseUrl + messagesUrl.replace("{inbox_id}", inboxId);
    }

    public String getMessageUrl(String inboxId, String messageId) {
        return baseUrl + messageUrl.replace("{inbox_id}", inboxId).replace("{message_id}", messageId);
    }

    public String buildUrl(String customEndpoint) {
        return baseUrl + customEndpoint;
    }

}
