package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataReaderCSV extends DataReader{
	public static void getMessagesFromCSVFiles(File file){
		Message message = null;
		BufferedReader br = null;
		try {
        	br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = br.readLine();
			while ((line = br.readLine()) != null)  {
				String[] field = line.split(",");
		         if(field.length<3)
		         	continue;
		         message = setMessage(message, line, field);
				addToHashMap(message);
             }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
        	try {
        		if (br != null)
                    br.close();
        	}catch (IOException e) {
        		e.printStackTrace();
            }
        }
	}

	private static Message setMessage(Message message, String line, String[] field) {
         String date = field[0];
         String user = field[1];
         String strMessage = field[2];
         strMessage = strMessage.replaceAll("\"", ""); 
         message = new Message(date, user, strMessage);
         return message;
	}

	private static void addToHashMap(Message message) {
		String user = message.getID();
		user = user.replaceAll("\"", "");
		if(!messages.containsKey(user)){
	        messages.put(user, new ArrayList<Message>());
	       	messages.get(user).add(message);
	    }
		if(messages.containsKey(message.getID())) {
	       	messages.get(user).add(message);
	     }
	}
}
