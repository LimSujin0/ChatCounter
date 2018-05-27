package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) throws IOException {
		HashMap<String, ArrayList<Message>> messages = new  HashMap<String, ArrayList<Message>>();
		System.out.println("Hello World!");
		String filepath = "C:\\Users\\imsuj\\Desktop\\java\\chat-java";	
		DataReader DR = new DataReader();
		messages = DR.getData(filepath);
	}

}
