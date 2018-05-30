package edu.handong.csee.java.chatcounter;
/**
 * This is a public class named Message
 * this Message is some object that is used a data structure
 * it has data, user, strMessage, and one method that return user 
 * @author imsuj
 *
 */
public class Message {
	String date, user, strMessage;
	/**
	 * This is a constructor that has three Strings as parameter
	 * that assign data, user, strMessage to all the parameters. 
	 * @param date
	 * @param user
	 * @param strMessage
	 */
	public Message(String date, String user, String strMessage) {
		this.date = date;
		this.user = user;
		this.strMessage = strMessage;	
	}
	/**
	 * this is a public method named getID that return user.
	 * @return
	 */
	public String getID(){
		return user;
	}
}
