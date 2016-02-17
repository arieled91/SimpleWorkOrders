package main.java.common;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

public class Utils {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String toString(Calendar date){
		return date!= null ? SDF.format(date.getTime()) : "";
	}
	
	public static Calendar fromString(String date){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(SDF.parse(date));
		} catch (ParseException e) {
            System.err.println("Error parsing string to calendar");
			e.printStackTrace();
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

    public static double parseDouble(String n){
        try{
            return Double.parseDouble(n);
        }catch(Exception e){
            return 0;
        }
    }
	
	public static void showMessageDialog(String message){
		JOptionPane.showMessageDialog(null, message, "Atención", JOptionPane.INFORMATION_MESSAGE);
	}

    public static boolean isInt(String n){
        try{
            Integer.parseInt(n);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isDouble(String n){
        try{
            Double.parseDouble(n);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isValidDate(int date, int month, int year){
        if(year > 2100 || year < Calendar.getInstance().get(Calendar.YEAR)) return false;
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,date);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean isValidDate(String date, String month, String year){
        return isValidDate(parseInt(date),parseInt(month),parseInt(year));
    }

    public static boolean areEquals(Calendar date1, Calendar date2){
        return date1.get(Calendar.DATE) == date1.get(Calendar.DATE) &&
                date1.get(Calendar.MONTH) == date1.get(Calendar.MONTH) &&
                date1.get(Calendar.YEAR) == date1.get(Calendar.YEAR);
    }


}
