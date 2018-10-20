package se.frihak.ticketplanner.kalender;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import se.frihak.ticketplanner.TicketplannerBase;

public class Dag extends TicketplannerBase {

	private LocalDate dagen;

	public Dag(String newDag) {
		dagen = LocalDate.parse(newDag);
	}

	public Dag(LocalDate enDag) {
		dagen = enDag;
	}

	public Dag getNextDag() {
		return plusDays(1);
	}

	@Override
	public boolean equals(Object arg0) {
		Dag andraDagen = (Dag) arg0;
		return dagen.isEqual(andraDagen.dagen);
	}

	@Override
	public String toString() {
		return dagen.toString();
	}

	public boolean isBefore(Dag anotherDay) {
		return dagen.isBefore(anotherDay.dagen);
	}

	public Dag plusDays(int i) {
		return new Dag(dagen.plusDays(i));
	}

	public String getWeekdayFormatted() {
		String veckodag = dagen.getDayOfWeek().getDisplayName(TextStyle.FULL , Locale.getDefault());
		String v1 = veckodag.substring(0, 1).toUpperCase();
		String v2 = veckodag.substring(1);

		return v1+v2;
	}
}
