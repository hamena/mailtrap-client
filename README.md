# Welcome
This utility is a Java wrapper around the MailTrap Restful API (http://docs.mailtrap.apiary.io/).

### Usage
Checkout the [Demo.java]

### Create a Mailtrap object

    	Mailtrap mailtrap = new Mailtrap(apiToken);
    	
    	
* Get your apiToken from https://mailtrap.io/public_api (you must be logged in)
    	
### Check for unread messages (Optional)

    	Inbox inbox = mailtrap.getInbox(inboxId);
	    inbox.getEmailsUnreadCount();
	    
	    
* Get your inboxId from the inbox URL, ex: https://mailtrap.io/inboxes/16667/messages  (16667 is the inboxId)	    
	   	    
### Get your messages
	    List<Message> messages = mailtrap.getMessages(inbox);
	    
	    if (messages.size() > 0){
        	Message msg = messages.get(0);
        	String html = msg.getHtml();
        }

* `messages.get(0)` returns the newest message

### Delete a message
		mailtrap.deleteMessage(inbox, msg);        

* Deleting messages is optional