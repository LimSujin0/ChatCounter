package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This is public class named MessageCounter
 * @author imsuj
 *
 */
public class MessageCounter {
	static HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
	/**
	 * This is a constructor that has HashMap<String, ArrayList<Message>> parameter
	 * this constructor set MessageCounter.messages to parameter.
	 * @param messages
	 */
	public MessageCounter(HashMap<String, ArrayList<Message>> messages) {
		MessageCounter.messages = messages;
	}
	/**
	 * This is a public method named messageCountSort
	 * this method has all methods in this class
	 * this method count message along keys
	 * and sort the message in descending order along the count number
	 * return sorterResult ArrayList<String>
	 * @return
	 */
	public ArrayList<String> messageCountSort(){
		HashMap<String, Integer> result = messageCount(messages);
		ArrayList<String> sortedResult = messageSort(result);
		return sortedResult;
	}

	private  ArrayList<String> messageSort(HashMap<String, Integer> result) {
		ArrayList<Integer> list = new ArrayList<Integer>(result.values());
		ArrayList<String> sortedResult = new ArrayList<String>();
		Collections.sort(list, Collections.reverseOrder());
		for(int i =0; i<list.size(); i++) {
			int count = list.get(i);
			String name = getKeyFromValue(result, count);
			String line = name + "," + count;
			sortedResult.add(line);
			//System.out.println(line);
			result.remove(name);
		}
		return sortedResult;
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