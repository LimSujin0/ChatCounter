package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class MessageCounter {
	static HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
	public MessageCounter(HashMap<String, ArrayList<Message>> messages) {
		MessageCounter.messages = messages;
	}
	
	public HashMap<String, Integer> messageCountSort(HashMap<String, ArrayList<Message>> message){
		HashMap<String, Integer> result = messageCount(messages);
		HashMap<String, Integer> sortResult = messageSort(result);
		
		return sortResult;
	}
	
	private  HashMap<String, Integer> messageSort(HashMap<String, Integer> result) {
		HashMap<String, Integer> sortResult = new HashMap<String, Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>(result.values());
		Collections.sort(list, Collections.reverseOrder());
		for(int i =0; i<list.size(); i++) {
			int count = list.get(i);
			String name = getKeyFromValue(result, count);
			sortResult.put(name, count);
			System.out.println(name+" "+sortResult.get(name));
			result.remove(name);
		}
		System.out.println("\n\n");
		for(String name : sortResult.keySet()) {
			System.out.println(name+" "+sortResult.get(name));
		}
		return sortResult;
	}
	
	public String getKeyFromValue(HashMap<String,Integer> result, Integer count) {
		  for (String name  : result.keySet()) {
			  if (result.get(name).equals(count)) {
				  return name;
			  }
		  }
		  return null;
	 }

	private HashMap<String, Integer> messageCount(HashMap<String, ArrayList<Message>> messages){
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		int count = 0;
		for(String keyID : messages.keySet()) {
			ArrayList<Message> msge = messages.get(keyID);
			count = msge.size();
			result.put(keyID, count);		
		}
		return result;
	}
	
}