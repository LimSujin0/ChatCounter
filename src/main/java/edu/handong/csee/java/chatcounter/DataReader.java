package edu.handong.csee.java.chatcounter;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.handong.csee.java.chatcounter.DataReaderCSV;
import edu.handong.csee.java.chatcounter.DataReaderTXT;
/**
 * This is a public class named Data Reader 
 * this class read some files in the path
 * generate new data in HashMap<String, ArrayList<Message>>
 * @author imsuj
 *
 */
public class DataReader {
	static HashMap<String, ArrayList<Message>> messages = new HashMap<String, ArrayList<Message>>();
	/**
	 * This is a public method named getData that get String parameter as file path
	 * this method bring some files in the path.
	 * and read the files and generate new data in form of HashMap<String, ArrayList<Message>>
	 * return HashMap<String, ArrayList<Message>>
	 * @param strDir
	 * @return
	 */
	public HashMap<String, ArrayList<Message>> getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFiles(myDir);
		readFiles(files);
		return messages;
	}

	private static File getDirectory(String fDir) {//get Directory and return File
		File myDirectory = new File(fDir);
		return myDirectory;
	}

	private static File[] getListOfFiles(File dataDir) {//get list of files
		return dataDir.listFiles();
	}

	/**
	 * This is a public method named reaFiles that get Files[] parameter
	 * this method read all the files in that list of files
	 * and read all data from these files
	 * generate new data in form of HashMap<String, ArrayList<Message>>
	 */
	public void readFiles(File[] files){//get String arraylist of the file	
		File file = null;
		for (int i = 0; i < files.length-1; i++){
			file = files[i];
			System.out.println("\nRead a file : " + file.getName());
			if(file.getName().endsWith(".csv"))
				DataReaderCSV.getMessagesFromCSVFiles(file);
			else
				DataReaderTXT.getMessagesFromTXTFiles(file);
		}	
	}
}	