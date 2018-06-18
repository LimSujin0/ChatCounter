package edu.handong.csee.java.chatcounter;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import edu.handong.csee.java.chatcounter.DataReaderCSV;
import edu.handong.csee.java.chatcounter.DataReaderTXT;
/**
 * This is a public class named Data Reader 
 * this class read some files in the path
 * generate new data in HashMap<String, ArrayList<Message>>
 * @author imsuj
 *
 */
public class DataReader{
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
		File myDirectory = new File("C:\\Users\\imsuj\\Desktop\\java\\chat-java");
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
		int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();
		System.out.println("the number of corse of my system: " + numOfCoresInMyCPU);
		ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);
		for (int i=0; i<files.length; i++ ){
			if(!files[i].getName().endsWith(".csv") && !files[i].getName().endsWith(".txt")){
				continue;
			}	
			System.out.println("Read a file : " + files[i].getName());
			if(files[i].getName().endsWith(".csv")) {
				Runnable worker = new Thread(new DataReaderCSV(files[i]));
				executor.execute(worker);
			}
			if(files[i].getName().endsWith(".txt")) {
				Runnable worker = new Thread(new DataReaderTXT(files[i]));
				executor.execute(worker);
			}	
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
			
		}
		
	
	}


}