package edu.handong.csee.java.chatcounter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * This is a public class named WriteCSV 
 * this class has createCSV method that write some data in CSV format
 * @author imsuj
 *
 */
public class WriteCSV{
	/**
	 * this is a public method named createCSV that has ArrayList<String> parameter 
	 *  this method writes some data in CSV format
	 * @param result
	 */
	public void createCSV(ArrayList<String> result, String output){	
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(output);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
			BufferedWriter fw = new BufferedWriter(outputStreamWriter);
			for(String line : result){
				System.out.println(line);
				fw.write(line+"\n");
			}
			fw.flush();
			fw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
