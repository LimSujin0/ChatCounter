package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * this is a public class named DataReaderCSV that extends DataReader
 * it reads all TXT files 
 * @author imsuj
 *
 */
public class DataReaderCSV extends DataReader{
	/**
	 * this is a public class named getMessagesFromCSVFiles that has File parameter
	 * this method reads all file lines
	 * generate new data in Message format, and add to HashMap
	 * @param file
	 */
	public static void getMessagesFromCSVFiles(File file){
		Message message = null;
		BufferedReader br = null;
		try {
        	br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = br.readLine();
            String[] field = null;
            while(((line = br.readLine())!=null)){
            	field = line.split(",");
            	if(field.length<3)
            		continue;
                message = setMessage(message, field);
                addToHashMap(message); 
            }
		}catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
        	try {
        		if(br != null)
        			br.close();
        	}catch (IOException e) {
        		e.printStackTrace();
            }
        }
	}

	private static Message setMessage(Message message, String[] field) {
         String date = field[0];
         String user = field[1];
         String strMessage = field[2];
         if(field.length>3){
        	 for(int i=3; i<field.length; i++) {
        		 strMessage = strMessage+"," +field[i];
        	 }
         }
         strMessage = strMessage.replaceAll("\"", ""); 
         message = new Message(date, user, strMessage);
         return message;
	}

	private static void addToHashMap(Message message) {
		String user = message.getID();
		user = user.replaceAll("\"", "");
		message.user = user;
		if(!messages.containsKey(user)){
	        messages.put(user, new ArrayList<Message>());
	       	messages.get(user).add(message);
	    }if(messages.containsKey(user)){
			messages.get(user).add(message);
	     }
	}
}
