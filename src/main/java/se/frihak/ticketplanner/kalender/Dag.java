package se.frihak.ticketplanner.kalender;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import se.frihak.ticketplanner.TicketplannerBase;

public class Dag extends TicketplannerBase {
	private String strDag;
	private Calendar dag = null;
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public Dag(String newDag) {
		strDag = newDag;
		dag = new GregorianCalendar();

		try {
			dag.setTime(formatter.parse(strDag));
		} catch (ParseException e) {
			strDag = "2999-12-31";
			dag.setTime(new Date(strDag));
		}
	}

	public Dag(Calendar nyDag) {
		strDag = formatter.format(nyDag.getTime());
		dag = nyDag;
	}

	public Dag getNextDag() {
		Dag nextDag = new Dag((Calendar) dag.clone());
		nextDag.addDays(1);
		return nextDag;
	}

	@Override
	public boolean equals(Object arg0) {
		Dag andraDagen = (Dag) arg0;

		if (strDag.equals(andraDagen.strDag)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return strDag;
	}

	public boolean before(Dag anotherDay) {
		return dag.before(anotherDay.dag);
	}

	public void addDays(int i) {
		dag.add(GregorianCalendar.DAY_OF_MONTH, i);
		strDag = formatter.format(dag.getTime());
		return;
	}

	@Override
	public Object clone() {
		Dag nyDag = new Dag((Calendar) dag.clone());

		return nyDag;
	}

	public String getWeekdayFormatted() {
		int veckodag = dag.get(Calendar.DAY_OF_WEEK);

		String[] dagar = new String[8];
		dagar[1] = "Söndag";
		dagar[2] = "Måndag";
		dagar[3] = "Tisdag";
		dagar[4] = "Onsdag";
		dagar[5] = "Torsdag";
		dagar[6] = "Fredag";
		dagar[7] = "Lördag";

		return dagar[veckodag];
	}

	public static Dag getToday() {
		// TODO kolla om denna metod verkligen används
		return new Dag(new GregorianCalendar());
	}
}
