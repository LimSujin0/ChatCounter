package edu.handong.csee.java.chatcounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;

public class WriteCSV{
	public int createCSV(HashMap<String, Integer> result){
		int resultCount =0;	
		try{
		    BufferedWriter fw = new BufferedWriter(new FileWriter("C:/Users/imsuj/Desktop/out.csv", true));
		    for(String user : result.keySet()){
		    	fw.write(user+","+result.get(user));
			 	fw.newLine();
		    }
			fw.flush();
		    fw.close();
		}catch (Exception e) {
		  e.printStackTrace();
		}
		return resultCount;
	}
}
