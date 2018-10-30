package se.frihak.ticketplanner;

import java.util.Comparator;

import se.frihak.ticketplanner.biljett.Biljettplan;

public class PriceComparator implements Comparator<Biljettplan> {

	@Override
	public int compare(Biljettplan plan1, Biljettplan plan2) {
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
