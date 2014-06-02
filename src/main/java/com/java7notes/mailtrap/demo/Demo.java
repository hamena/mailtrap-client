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
