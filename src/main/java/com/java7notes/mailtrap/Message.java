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
 * Represents an Inbox message, use getHtml() to retrieve its contents.
 * 
 * @author jxc876
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	
	private String id;
	
	private String subject;
	
	@JsonProperty("from_email")
	private String fromEmail;

	@JsonProperty("from_name")
	private String fromName;
	
	@JsonProperty("to_email")
	private String toEmail; 

	@JsonProperty("to_name")
	private String toName;

	@JsonProperty("is_read")
	private boolean isRead;

	@JsonProperty("email_size")
	private int emailSize;

	@JsonProperty("html_body_size")
	private int htmlBodySize;

	@JsonProperty("text_body_size")
	private int textBodySize;

	@JsonProperty("human_size")
	private String humanSize;

	@JsonProperty("html_path")
	private String htmlPath;

	@JsonProperty("html_source_path")
	private String htmlSourcePath;
	
	@JsonProperty("text_path")
	private String textPath;

	@JsonProperty("raw_path")
	private String rawPath;
	
	private MailtrapAPI apirest;

	private String htmlBody=null, htmlSourceBody=null, textBody=null;

	public Message(){
		// :)
	}

	public String getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public String getToEmail() {
		return toEmail;
	}

	public String getToName() {
		return toName;
	}

	public boolean isRead() {
		return isRead;
	}

	public int getSize() {
		return emailSize;
	}

	public int getHtmlSize() {
		return htmlBodySize;
	}

	public int getTextSize() {
		return textBodySize;
	}

	public String getHumanSize() {
		return humanSize;
	}

	public String getHtml() {
		if (htmlBody == null)
			htmlBody = htmlBodySize==0 ? "" : apirest.configure(apirest.buildUrl(htmlPath)).get(String.class);
		return htmlBody;
	}

	public String getHtmlSource() {
		if (htmlSourceBody == null) 
			htmlSourceBody = htmlBodySize==0 ? "" : apirest.configure(apirest.buildUrl(htmlSourcePath)).get(String.class);
		return htmlSourceBody;
	}

	public String getText() {
		if (textBody == null)
			textBody = textBodySize==0 ? "" : apirest.configure(apirest.buildUrl(textPath)).get(String.class);
		return textBody;
	}

	public String getRaw() {
		if (emailSize == 0) 
			return "";
		else
			return apirest.configure(rawPath).get(String.class);
	}
	
	public void setup(MailtrapAPI apirest) {
		this.apirest = apirest;
	}
	
	@Override
    public String toString() {
	    return "Message [id=" + id + ", subject=" + subject + ", fromEmail="
	            + fromEmail + ", toEmail=" + toEmail + "]";
    }
	
}
