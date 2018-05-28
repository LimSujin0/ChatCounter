package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) throws IOException {
		HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		System.out.println("Hello World!");
		String filepath = "C:\\Users\\imsuj\\Desktop\\java\\chat-java";	
		DataReader DR = new DataReader();
		messages = DR.getData(filepath);
		
		MessageFilter MF = new MessageFilter(messages);
		messages = MF.MessageFilt(messages);
		
		MessageCounter MC = new MessageCounter(messages);
		result = MC.messageCountSort(messages);
		/*for(String keyID : result.keySet()) {
			System.out.println(keyID+" " + result.get(keyID));
		}*/
		
		WriteCSV WC = new WriteCSV();
		WC.createCSV(result);
		
		System.out.println("finish!");
	}
}
