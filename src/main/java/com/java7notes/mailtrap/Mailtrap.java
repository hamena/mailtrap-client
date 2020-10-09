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

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * A Java wrapper around the MailTrap Restful API (http://docs.mailtrap.apiary.io/)
 * 
 * This class requires that you know your API key & inbox id.
 * 
 * 
 * api key
 * --------
 * To get your API key, login into Mailtrap and navigate to the following URL:
 * https://mailtrap.io/public_api
 * 
 * inbox id
 * ---------
 * You can find the Inbox id, by looking at the url:
 * https://mailtrap.io/inboxes/16667/messages  (16667 is the inbox id)
 * 
 * Look at com.java7notes.mailtrap.demo.Demo for example usage.
 * 
 * 
 * 
 * @author jxc876
 */
public class Mailtrap {
	
	private MailtrapAPI apirest;
	
	public Mailtrap(String apiToken){
		this.apirest = new MailtrapAPI(apiToken);
	}
	
	public Inbox getInbox(String inboxId){
    	String url = apirest.getInboxUrl(inboxId);
		Inbox inbox = apirest.configure(url).get(Inbox.class);
		return inbox;
	}

	public List<Message> getMessages(Inbox inbox){
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		for (Message msg : msgs) {
			msg.setup(apirest);
		}
		return msgs;
	}
	
	
	public void deleteMessage(Inbox inbox, Message msg){
		String url = apirest.getMessageUrl(inbox.getId(), msg.getId());
		apirest.configure(url).delete();
	}

	/*
	 * The MailTrap API requires using PATCH, however Jersey client doesn't support it.
	 * I tried using the 'X-HTTP-Method' workaround but doesn't seem to be supported.
	 * TODO: Use 'Apache Http Client' for PATCH requests.
	 * @param inbox
	 *
	private final String cleanInboxUrl = "/api/v1/inboxes/{inbox_id}/clean";
	public void cleanInbox(Inbox inbox) {
		System.out.println("cleaning inbox");
	    String url = cleanInboxUrl.replace("{inbox_id}", inbox.id);
	    Response response = configure(url).header("X-HTTP-Method-Override", "PATCH").get();
	    System.out.println("status = " + response.getStatus());
	}
	*/
	
}
