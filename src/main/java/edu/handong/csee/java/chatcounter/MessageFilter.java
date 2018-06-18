package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This is a public class named MessageFilter
 * this class has MessageFilt method that filter same message.
 * @author imsuj
 *
 */
public class MessageFilter{
	static HashMap<String, ArrayList<Message>> messages = null;
	/**
	 * this is a public constructor of this class that has HashMap<String, ArrayList<Message>> parameter
	 * this parameter set MessageFilter.messages to parameter
	 * @param messages
	 */
	public MessageFilter(HashMap<String, ArrayList<Message>> messages) {
		MessageFilter.messages = messages;
	}
	/**
	 * this is a public method named MessageFilt that has HashMap<String, ArrayList<Message>> paratmeter
	 * this method compare the time and message of key's data
	 * and if there is a same data, remove that data.
	 * after that return HashMap<String, ArrayList<Message>
	 * @param messages
	 * @return
	 */
	public HashMap<String, ArrayList<Message>> MessageFilt(HashMap<String, ArrayList<Message>> messages) {
		for(String keyID : messages.keySet()) {
			for (int i = 0; i < messages.get(keyID).size()-1; i++) {
				for(int j = i+1; j <messages.get(keyID).size(); j++) {
					if(compareTime(messages.get(keyID).get(i), messages.get(keyID).get(j))) {
						messages.get(keyID).remove(j--);

					}
				}
			}
		}
		return messages;
	}

	private boolean compareTime(Message message1, Message message2) {
		String date1 = message1.date;
		String date2 = message2.date;
		if(date1.length()>16 && date2.length()>16){//these date has hours, minutes and seconds
			if(date1.equals(date2)) {
				return compareMessage(message1, message2);
			}
		}else{
			if(date1.substring(0,16).equals(date2.substring(0,16))) {			
				return compareMessage(message1, message2);
			}
		}
		return false;
	}

	private boolean compareMessage(Message message1, Message message2) {
		String msge1 = message1.strMessage;
		String msge2 = message2.strMessage;
		if(msge1.equals(msge2))
			return true;
		return false;
	}

}
