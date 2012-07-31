package niord.apps.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Race {

	String cirquit;
	String grandprix;
	Date date;

	long days;
	long hours;
	long minutes;
	long seconds;

	public Race(String cirquit, String grandprix, Date date) {
		super();
		this.cirquit = cirquit;
		this.grandprix = grandprix;
		this.date = date;
	}

	/**
	 * Sets the difference between next race and current time in m/h/d
	 * 
	 * @param diff
	 *           Difference in milliseconds
	 * @return True if it is next race
	 */
	public boolean getDiff() {
		Date curdate = new Date();

		// If the race is after current date, calculate the difference in time
		if (date.after(curdate)) {
			// date.getTime() - curdate.getTime() = difference in milliseconds
			seconds = (date.getTime() - curdate.getTime()) / 1000;
			minutes = seconds / 60;
			hours = minutes / 60;
			days = hours / 24;
			return true;
		}
		return false;

	}

	/**
	 * @return the date
	 */
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm");
		df.setTimeZone(TimeZone.getDefault());
		return df.format(date);
	}

	/**
	 * @return the cirquit
	 */
	public String getCirquit() {
		return cirquit;
	}

	/**
	 * @return the grandprix
	 */
	public String getGrandprix() {
		return grandprix;
	}

	/**
	 * @return the days
	 */
	public long getDays() {
		return days;
	}

	/**
	 * @return the hours
	 */
	public long getHours() {
		return hours;
	}

	/**
	 * @return the minutes
	 */
	public long getMinutes() {
		return minutes;
	}

	/**
	 * @return the seconds
	 */
	public long getSeconds() {
		return seconds;
	}
}
