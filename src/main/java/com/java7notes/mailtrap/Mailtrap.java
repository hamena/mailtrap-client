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

import javax.ws.rs.core.GenericType;

/**
 * @author jxc876
 * @author hamena
 */
public class Mailtrap {
	
	private MailtrapAPI apirest;
	
	public Mailtrap(String apiToken){
		this.apirest = new MailtrapAPI(apiToken);
	}

	public User getUser() {
		String url = apirest.getUserUrl();
		return apirest.configure(url).get(User.class);
	}
	
	public Inbox getInbox(String inboxId){
    	String url = apirest.getInboxUrl(inboxId);
		return apirest.configure(url).get(Inbox.class);
	}

	public List<Message> getMessages(Inbox inbox){
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		for (Message msg : msgs) {
			msg.setup(apirest);
		}
		return msgs;
	}
	
	public Message getMessage(Inbox inbox, String from, boolean isRead) {
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		long current = System.currentTimeMillis();
		long end = current + 10000;
		while (current < end) {
			for (Message msg : msgs) {
				if (msg.getFromEmail().equals(from) && msg.isRead() == isRead) {
					msg.setup(apirest);
					return msg;
				}
			}
			current = System.currentTimeMillis();
		}
		return null;
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
