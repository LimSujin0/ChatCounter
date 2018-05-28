package edu.handong.csee.java.chatcounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataWriter {
	public void fileWirte(HashMap<String, Integer> result) throws IOException{
		try{
			FileWriter fw = new FileWriter("C:/out.csv");
			//BufferedWriter bw = new BufferedWriter(fw);
			for(String user : result.keySet()){
				String line  = user + "," + result.get(user)+"\n";
			}
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}	
}