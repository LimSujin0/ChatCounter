package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This is a public class named DataReaderTXT that extends Data Reader
 * it reads all TXT files
 * @author imsuj
 *
 */
public class DataReaderTXT extends DataReader implements Runnable {
	enum Months{Jenuary, Febuary, March, April, May, June, July, August, September, October,  November, December};
	/**this is a public method named getMEssagesFromTXTFiles
	 * this method find some pattern
	 * and generate messages from one line to message, user, date along the pattern
	 * @param file
	 */
	File file;
	public DataReaderTXT(File file) {
		this.file = file;
	}
	public void run() {
		getMessagesFromTXTFiles(file);
	}
	
	public static void getMessagesFromTXTFiles(File file){
		String thisLine = null;
		String date = null;
		BufferedReader br = null;
		Message message = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			thisLine = br.readLine();
			while ((thisLine = br.readLine()) != null) {
				String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s.+\\s-+";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(thisLine);
				String pattern1 = "-+\\s.+\\s(.+)\\s([0-9]+).\\s([0-9]+)\\s-+";
				Pattern r1 = Pattern.compile(pattern1);
				Matcher m1 = r1.matcher(thisLine);
				String pattern2 = "\\[(.+)]\\s\\[..\\s([0-9]+):([0-9]+)\\]\\s(.+)";
				Pattern r2 = Pattern.compile(pattern2);
				Matcher m2 = r2.matcher(thisLine);

				if(m.find()||m1.find()){
					date = setDate(message, thisLine);
				}if(m2.find()){
					message = setMessage(thisLine, date);				
					addToHashMap(message);
				}
			}
			br.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addToHashMap(Message message) {
		String user = message.getID();
		if(!messages.containsKey(user)){
			messages.put(user , new ArrayList<Message>());
			messages.get(message.getID()).add(message);
		}
		if(messages.containsKey(user)) {
			ArrayList<Message> m = messages.get(message.getID());
			m.add(message);
		}	
	}

	private static Message setMessage(String line, String date) {
		Message message = null;
		String pattern2 = "\\[(.+)]\\s\\[(..)\\s([0-9]+):([0-9]+)\\]\\s(.+)";
		Pattern r2 = Pattern.compile(pattern2);
		Matcher m2 = r2.matcher(line);
		if(m2.find()){
			String user = m2.group(1);
			String time = changeAmPm(m2.group(2), m2.group(3), m2.group(4));
			String dateA = date + time;
			String strMessage = m2.group(5);
			if(strMessage.equals("사진")) {
				strMessage = "Photo";
			}
			strMessage = strMessage.replaceAll("\"", "");
			message = new Message(dateA, user, strMessage);	
		}

		return message;
	}

	private static String changeAmPm(String ap, String hour, String minute) {
		String time = null;
		if(ap.equals("오후")&&!hour.equals("12")) {
			hour =  String.valueOf(Integer.parseInt(hour)+12);
			time = hour+":"+minute;
		}if(ap.equals("오후")&&hour.equals("12")) {
			time = hour+":"+minute;
		}if(ap.equals("오전")&&hour.equals("12")) {
			hour =  "00";
			time = hour+":"+minute;
		}if(ap.equals("오전")&&!hour.equals("12")) {
			hour =String.format("%02d",Integer.parseInt(hour));
			time = hour+":"+minute;
		}

		if(minute.equals("PM")) {
			ap =  String.valueOf(Integer.parseInt(ap)+12);
			time = ap+":"+hour;
		}if(minute.equals("AM")&&ap.equals("12")) {
			ap =  "00";
			time = ap+":"+hour;
		}if(minute.equals("AM")&&!ap.equals("12")){
			ap =String.format("%02d",Integer.parseInt(ap));
			time = ap+":"+hour;
		}

		return time;
	}

	private static String setDate(Message message, String line) {
		String date = null; 
		String pattern2 = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s.+\\s-+";
		Pattern r = Pattern.compile(pattern2);
		Matcher m = r.matcher(line);
		String pattern1 = "-+\\s.+\\s(.+)\\s([0-9]+).\\s([0-9]+)\\s-+";
		Pattern r1 = Pattern.compile(pattern1);
		Matcher m1 = r1.matcher(line);
		if(m.find()) {
			String year = m.group(1);
			String month =String.format("%02d",Integer.parseInt(m.group(2)));
			String day = String.format("%02d",Integer.parseInt(m.group(3)));
			date = year+"-"+month+"-"+day+" ";
		}if(m1.find()){
			Months month = Months.valueOf(m1.group(1));
			String day = String.format("%02d",Integer.parseInt(m1.group(2)));
			String year = m1.group(3);
			date = year+"-"+month+"-"+day+" ";
		}
		return date;
	}
}

