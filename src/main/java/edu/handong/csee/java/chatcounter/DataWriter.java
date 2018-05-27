package edu.handong.csee.java.chatcounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataWriter {
	public void fileWirte(ArrayList<String> result) throws IOException{
		PrintWriter pw = new PrintWriter("C:/out.csv");
		//write the result in the file
		pw.close();//save the file
	}
}
