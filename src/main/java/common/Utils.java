package main.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.JOptionPane;

public class Utils {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String toString(Calendar date){
		return SDF.format(date.getTime());
	}
	
	public static Calendar fromString(String date){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(SDF.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Error parsing string to calendar");
			return null;
		}
		
		return calendar;
	}
	
	public static Calendar fromInt(int date, int month, int year){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(year, month, date);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error invalid date");
			return null;
		}
		
		return calendar;
	}

	public static boolean isEmpty(String s){
		return s==null || s.equals("");
	}
	
	 public static boolean isEmpty(Collection<?> collection) {
		 return collection == null || collection.isEmpty();
	 }
	 
	public static boolean areEquals(String a, String b){
		return a.trim().toLowerCase().equals(b.trim().toLowerCase());
	}
	
	public static int parseInt(String n){
		try{
			return Integer.parseInt(n);
		}catch(Exception e){
			return 0;
		}
	}
	
	public static void showMessageDialog(String message){
		JOptionPane.showMessageDialog(null, message, "Atención", JOptionPane.INFORMATION_MESSAGE);
	}

}
