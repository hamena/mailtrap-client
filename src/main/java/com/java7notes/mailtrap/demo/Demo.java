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

package com.java7notes.mailtrap.demo;

import java.util.List;

import com.java7notes.mailtrap.Inbox;
import com.java7notes.mailtrap.Mailtrap;
import com.java7notes.mailtrap.Message;

/**
 * This class shows how to use the MailTrap classes,
 * Just update the apiToken & inboxId, then give it a try.
 * 
 * @author jxc876
 */
public class Demo 
{
    public static void main( String[] args )
    {
    	// insert your token & inbox id
    	String apiToken = "99999999999999999";
    	String inboxId = "12345";
    	
    	Mailtrap mailtrap = new Mailtrap(apiToken);
    	Inbox inbox = mailtrap.getInbox(inboxId);
    	    	
    	System.out.println("unread messages = " + inbox.getEmailsUnreadCount());
    	
    	List<Message> messages = mailtrap.getMessages(inbox);
    	
    	if (messages.size() > 0){
        	Message msg = messages.get(0);
        	String html = msg.getHtml();
        	
        	// verify your HTML contents  
        	
        	// you can delete the message if you no longer need it
        	mailtrap.deleteMessage(inbox, msg);
    	}
    	else{
    		System.out.println("No messages found");
    	}
    }
}
