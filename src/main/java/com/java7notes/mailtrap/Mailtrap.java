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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.core.GenericType;

/**
 * @author jxc876
 * @author hamena
 */
public class Mailtrap {
	
	private MailtrapAPI apirest;

	private long maxWaitMillis = 10000L;
	
	public Mailtrap(String apiToken){
		this.apirest = new MailtrapAPI(apiToken);
	}

	public void setImplicitWait(int seconds) {
		maxWaitMillis = seconds * 1000;
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
	
	public List<Message> getMessages(Inbox inbox, SearchCriteria criteria) {
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		List<Message> filtered = new ArrayList<>();
		for (Message msg : msgs) {
			if (criteria.evaluate(msg)) {
				msg.setup(apirest);
				filtered.add(msg);
			}
		}
		return filtered;
	}

	public Message getLastMessage(Inbox inbox) {
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		Message lastMessage = msgs.isEmpty() ? null : msgs.get(msgs.size()-1);
		if (lastMessage != null) {
			lastMessage.setup(apirest);
		}
		return lastMessage;
	}
	
	public Message getMessage(Inbox inbox, SearchCriteria criteria) throws TimeoutException {
		String url = apirest.getMessagesUrl(inbox.getId());
		List<Message> msgs = apirest.configure(url).get(new GenericType<List<Message>>(){});
		long current = System.currentTimeMillis();
		long end = current + maxWaitMillis;
		while (current < end) {
			for (Message msg : msgs) {
				if (criteria.evaluate(msg)) {
					msg.setup(apirest);
					return msg;
				}
			}
			current = System.currentTimeMillis();
		}
		throw new TimeoutException("There aren't any message that satisfies search criteria in given time");
	}
	
	public void deleteMessage(Inbox inbox, Message msg){
		String url = apirest.getMessageUrl(inbox.getId(), msg.getId());
		apirest.configure(url).delete();
	}
}
