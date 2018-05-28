package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFilter{
	static HashMap<String, ArrayList<Message>> messages = null;
	public MessageFilter(HashMap<String, ArrayList<Message>> messages) {
		MessageFilter.messages = messages;
	}

	public HashMap<String, ArrayList<Message>> MessageFilt(HashMap<String, ArrayList<Message>> messages) {
		for(String keyID : messages.keySet()) {
			ArrayList<Message> arrayList = messages.get(keyID);
			for (int i = 0; i < arrayList.size()-1; i++) {
				for(int j = i+1; i < arrayList.size(); i++) {
					if(compareTime(arrayList.get(i), arrayList.get(j)))
						arrayList.remove(i);
				}
			}
		}
		return messages;
	}

	private boolean compareTime(Message message1, Message message2) {
		String date1 = message1.date;
		String date2 = message2.date;
		if(date1.length()==19 && date1.length()==date2.length()){//these date has hours, minutes and seconds
			if(date1.equals(date2)) 
				return compareMessage(message1, message2);
		}else{
			if(date1.substring(0,16).equals(date2.substring(0,16)))
				return compareMessage(message1, message2);
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
