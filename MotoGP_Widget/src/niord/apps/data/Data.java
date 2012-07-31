package niord.apps.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import android.util.Log;

public class Data {

	public static String[] Cirquit = { "Losail Circuit", "Jerez", "Estoril Circuit", "Le Mans", "Circuit de Catalunya",
			"Silverstone", "Assen", "Sachsenring", "Mugello", "Mazda Raceway", "Indianapolis Motor", "Automotodrom Brno",
			"Misano World Circuit", "Motorland Aragón", "Motegi", "Sepang Circuit", "Phillip Island", "Comunitat Valenciana" };

	public static String[] GrandPrix = { "Qatar", "Spain", "Portugal", "France", "Catalunya", "Great Britain", "Netherlands",
			"Germany", "Italy", "United States", "Indianapolis", "Czech Republic", "San Marino", "Aragon", "Japan", "Malaysia",
			"Australia", "Valencia" };

	public static String[] RaceDate = { "8-04-12 14:00", "29-04-12 14:00", "6-05-12 14:00", "20-05-12 14:00", "3-06-12 14:00",
			"17-06-12 14:00", "30-06-12 14:00", "8-07-12 13:00", "15-07-12 14:00", "29-07-12 14:00", "19-08-12 14:00",
			"26-08-12 14:00", "16-09-12 14:00", "30-09-12 14:00", "14-10-12 14:00", "21-10-12 16:00", "28-10-12 16:00",
			"11-11-12 14:00" };

	public static String[] GMT = { "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT+2", "GMT-7",
			"GMT-4", "GMT+2", "GMT+2", "GMT+2", "GMT+9", "GMT+8", "GMT+11", "GMT+1" };

	public static int count;
	public static int err = 0;

	public static ArrayList<Race> races = new ArrayList<Race>();

	public static Race getNextRace() {

		int i = 0;
		while (!races.get(i).getDiff()) {
			i++;
		}

		return races.get(i);
	}

	static {
		count = Cirquit.length;
		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm");

		for (int i = 0; i < Cirquit.length; i++) {
			try {
				df.setTimeZone(TimeZone.getTimeZone(GMT[i]));
				Date date = (Date) df.parse(RaceDate[i]);

				races.add(new Race(Cirquit[i], GrandPrix[i], date));
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.w("WidgetExample", e.getMessage());
				err++;
				e.printStackTrace();
			}
		}
	}
}
