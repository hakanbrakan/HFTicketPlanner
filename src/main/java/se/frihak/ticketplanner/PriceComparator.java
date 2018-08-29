package se.frihak.ticketplanner;

import java.util.Comparator;

import se.frihak.ticketplanner.biljett.Biljettplan;

public class PriceComparator implements Comparator<Biljettplan> {

	public int compare(Biljettplan arg0, Biljettplan arg1) {
		Biljettplan plan1 = arg0;
		Biljettplan plan2 = arg1;

		int result = 0;
		if (plan1.getPrice() < plan2.getPrice()) {
			result = -1;
		} else if (plan1.getPrice() > plan2.getPrice()) {
			result = 1;
		} else {
			result = 0;
		}

		return result;
	}

}
