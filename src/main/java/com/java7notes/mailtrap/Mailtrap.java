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
	
	public final String baseUrl = "https://mailtrap.io";
	private final String inboxUrl = "/api/v1/inboxes/{inbox_id}"; 
    private final String messagesUrl = "/api/v1/inboxes/{inbox_id}/messages";
	private final String deleteMsg = "/api/v1/inboxes/{inbox_id}/messages/{id}";

    
	private String apiToken;
	
	public Mailtrap(String apiToken){
		this.apiToken = apiToken;
	}
	
	private Builder configure(String url){
    	Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    	WebTarget webTarget = client.target(baseUrl + url).queryParam("api_token", apiToken);
    	return webTarget.request(MediaType.APPLICATION_JSON_TYPE);
	}
	
	
	public Inbox getInbox(String inboxId){
    	String url = inboxUrl.replace("{inbox_id}", inboxId);
    	Inbox inbox = configure(url).get(Inbox.class);	
		return inbox;
	}

	public List<Message> getMessages(Inbox inbox){
		String url = messagesUrl.replace("{inbox_id}", inbox.getId());
		List<Message> msgs = configure(url).get(new GenericType<List<Message>>(){});
		return msgs;
	}
	
	
	public void deleteMessage(Inbox inbox, Message msg){
		String url = deleteMsg.replace("{inbox_id}", inbox.getId());
		url = url.replace("{id}", msg.getId());
		configure(url).delete();
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
