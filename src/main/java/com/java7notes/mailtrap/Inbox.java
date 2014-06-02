package com.java7notes.mailtrap;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

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
