package se.frihak.ticketplanner;

import java.util.Comparator;

import se.frihak.ticketplanner.biljett.Biljettplan;

public class LastDateComparator implements Comparator<Biljettplan> {

	public int compare(Biljettplan arg0, Biljettplan arg1) {
		Biljettplan plan1 = arg0;
		Biljettplan plan2 = arg1;

		int result = 0;
		if (plan1.getSistaGiltighetsdag().isBefore(plan2.getSistaGiltighetsdag())) {
			result = -1;
		} else if (plan2.getSistaGiltighetsdag().isBefore(plan1.getSistaGiltighetsdag())) {
			result = 1;
		} else {
			// Samma sista giltighetsdag, Sortera då på pris under samma datum

			PriceComparator priceComp = new PriceComparator();
			result = priceComp.compare(arg0, arg1);
		}

		return result;
	}
}
