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
 * Contains details about a particular Mailtrap inbox.
 * 
 * You can use getEmailsCount() & getEmailsUnreadCount() to check for messages 
 * before asking Mailtrap to retrieve messages.
 * 
 * @author jxc876
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inbox {

	private String id;
	private String name;

	@JsonProperty("emails_count")
	private int emailsCount;

	@JsonProperty("emails_unread_count")
	private int emailsUnreadCount;

	public Inbox() {
		// JAXB
	}
	
	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getEmailsCount() {
		return emailsCount;
	}


	public int getEmailsUnreadCount() {
		return emailsUnreadCount;
	}


	@Override
    public String toString() {
	    return "Inbox [id=" + id + ", name=" + name + ", emailsCount="
	            + emailsCount + ", emailsUnreadCount=" + emailsUnreadCount
	            + "]";
    }

}
