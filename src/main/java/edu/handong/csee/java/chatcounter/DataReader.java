package edu.handong.csee.java.chatcounter;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.handong.csee.java.chatcounter.DataReaderCSV;
import edu.handong.csee.java.chatcounter.DataReaderTXT;

public class DataReader {
	static HashMap<String, ArrayList<Message>> messages = new HashMap<String, ArrayList<Message>>();
	public HashMap<String, ArrayList<Message>> getData(String strDir){
		File myDir = getDirectory(strDir);
		File[] files = getListOfFiles(myDir);
		readFiles(files);	
		return messages;
	}
	
	public File getDirectory(String fDir) {//get Directory and return File
		File myDirectory = new File(fDir);
		return myDirectory;
	}
	
	public File[] getListOfFiles(File dataDir) {//get list of files
		return dataDir.listFiles();
	}
	
	public void readFiles(File[] files){//get String arraylist of the file	
		File file = null;
		for (int i = 0; i < files.length; i++){
			file = files[i];
			System.out.println("\nRead a file : " + file.getName());
			if(file.getName().endsWith(".csv"))
				DataReaderCSV.getMessagesFromCSVFiles(file);
			else
				DataReaderTXT.getMessagesFromTXTFiles(file);
		}	
	}
}	