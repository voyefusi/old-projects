package newsManager;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents time using hours and minutes (seconds are not 
 * represented). We have implemented this class for you and 
 * you should not modify it.
 *  
 * @author Dept of Computer Science, UMCP
 */

public class Time implements Comparable<Time>, Cloneable {
	int minutesFromMidnight;
	private static final int MINUTES_IN_A_DAY = 1440;
	
	/**
	 * Initializes the object using specified parameter values.
	 * 
	 * @param hour value between 1 and 12, both inclusive
	 * @param minutes value between 0 and 59, both inclusive
	 * @param amPm	either "am" or "pm"
	 * @throws IllegalArgumentException 
	 * 		Thrown for invalid hour, minutes, or amPm value. Validity for hour is 
	 * 	    verified first, followed by the minutes' validity and the am/pm 
	 * 		value's  validity.  The thrown exception must use one of the following
	 * 		messages:   "Invalid hour value", "Invalid minutes value", or
	 * 		"Invalid am/pm value".
	 */
	public Time(int hour, int minutes, String amPm){
		init(hour, minutes, amPm);
	}
	
	/**
	 * Initializes the object using the specified string.
	 * 
	 * @param timeStr string with format hh:mm am / hh:mm pm
	 * 
	 * @throws IllegalArgumentException 
	 * 		Thrown for invalid hour, minutes, or amPm value. Validity for hour is 
	 * 	    verified first, followed by the minutes' validity and the am/pm 
	 * 		value's  validity.  The thrown exception must use one of the following
	 * 		messages:   "Invalid hour value", "Invalid minutes value", or
	 * 		"Invalid am/pm value".
	 */
	public Time(String timeStr) {
		ArrayList<String> components = parseTimeString(timeStr);
		if (components == null)
			throw new IllegalArgumentException("Invalid time string format");
		init(Integer.parseInt(components.get(0)), Integer.parseInt(components.get(1)), 
			 components.get(2));
	}
	
	/**
	 * Returns a string using the format "hour:minutes am" or "hour:minutes pm".
	 * A single space is used in between minutes and am/pm.  The minutes always
	 * appear with two digits (e.g., 0 minutes will be "00").
	 * 
	 * @return string representation for the Time object
	 */
	public String toString() {
		int hour = minutesFromMidnight / 60;
		int minutes = minutesFromMidnight % 60;
		String ampm;
		
		if (hour == 0) {
			ampm = "am";
			hour = 12;
		} else if (hour == 12){
			ampm = "pm";
		} else if (hour > 12) {
			hour -= 12;
			ampm = "pm";
		} else {
			ampm = "am";
		}	
		
		return hour + ":" + ((minutes <= 9) ? ("0"+minutes) : minutes) + " " + ampm;
	}
	
	/**
	 * Compares two time objects.  Two time objects are considered equal if
	 * they represent the same time.
	 * 
	 * @return true if the objects are considered equal and false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Time))
			return false;
		else {
			Time time = (Time)obj;
			return (compareTo(time)==0 ? true : false);
		}
	}
	
	/**
	 * Returns a hash code for the object based on the string representation
	 * for the object.
	 * 
	 * @return hash code value
	 */
	public int hashCode() {
		return minutesFromMidnight;
	}
	
	/**
	 * Returns a duplicate (shallow copy) of the object.
	 * 
	 * @return duplicate
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Compares two time objects. Returns -1 if the current object is a time
	 * that precedes the time parameter, 0 if the current object and the time
	 * parameter represent the same time, and 1 if the current object represents
	 * a time that is after the time parameter.
	 * 
	 * @return -1, 0, or 1
	 */
	public int compareTo(Time time) {
		if (this.minutesFromMidnight < time.minutesFromMidnight)
			return -1;
		if (this.minutesFromMidnight == time.minutesFromMidnight)
			return 0;
		return 1;
	}
	
	/**
	 * Returns a new time object corresponding to the time we will have after
	 * increasing the time parameter by the specified number of minutes. Notice 
	 * that the number of minutes could exceed the number of minutes in a day,
	 * in which case the resulting time for the next day should be returned.
	 * <br><br>
	 * Keep in mind that the time parameter is not modified by this method.
	 * 
	 * @param time 
	 * @param minutes delta to apply to time parameter
	 * @return time object with delta applied
	 */
	public static Time increaseByMinutes(Time time, int minutes) {
		Time newTime = (Time)time.clone();
		newTime.minutesFromMidnight += minutes;
		newTime.minutesFromMidnight %= MINUTES_IN_A_DAY;
		
		return newTime;
	}
	
	/**
	 * Parses string object representing time returning array.
	 * with hour, minutes, am or pm component.
	 * @param timeStr string with format hh:mm am / hh:mm pm.  Spaces may exist
	 * between mm and am, mm and pm and after am or pm
	 * @return
	 */
	public static ArrayList<String> parseTimeString(String timeStr) {
		Pattern p = Pattern.compile("([1-9]|10|11|12):([0-5]\\d)\\s*?(am|pm)\\s*?");
		Matcher m = p.matcher(timeStr);
		if (!m.matches())
			return null;
		else {
			ArrayList<String> answer = new ArrayList<String>();
			answer.add(m.group(1));
			answer.add(m.group(2));
			answer.add(m.group(3));
			return answer;
		}
	}
	
	/* private method */
	public void init(int hour, int minutes, String amPm){
		if (hour < 0 || hour > 12)
			throw new IllegalArgumentException("Invalid hour value");

		if (minutes < 0 || minutes > 59)
			throw new IllegalArgumentException("Invalid minutes value");

		if (!amPm.equals("am") && !amPm.equals("pm"))
			throw new IllegalArgumentException("Invalid am/pm value");
		
		int totalHours;
		if (amPm.equals("am")) {
			if (hour == 12)
				totalHours = 0;
			else
				totalHours = hour;
		}
		else { 
			if (hour == 12)
				totalHours = 12;
			else
				totalHours = hour + 12;
		}
		
		minutesFromMidnight = totalHours * 60 + minutes;
	}
}