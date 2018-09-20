package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import se.frihak.ticketplanner.Ticketcreator;
import se.frihak.ticketplanner.TicketplannerBase;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Biljettplan extends TicketplannerBase {
	private List<Biljett> biljetter = new ArrayList<Biljett>();

	public Biljettplan(Biljettplan biljettplan, Biljett bilj) {
		biljetter.addAll(biljettplan.biljetter);
		biljetter.add(bilj);
	}

	public Biljettplan() {
	}

	public List<Biljettplan> planera(Resa resa, Ticketcreator ticketcreator) {
		List<Biljettplan> nyaPlaner = new ArrayList<Biljettplan>();

		// Kolla om denna plan redan har en giltig biljett. Om ja, så returneras denna
		// plan
		if (biljetter.size() > 0) {
			Biljett biljett = biljetter.get(biljetter.size() - 1);
			if (biljett.isValid(resa)) {
				biljett.add(resa);
				nyaPlaner.add(this);
				return nyaPlaner;
			}
		}

		// Skapa nya planer, en för varje biljetttyp
		for (Biljett oneTicket : ticketcreator.createAllTickets(resa)) {
			nyaPlaner.add(new Biljettplan(this, oneTicket));
		}

		return nyaPlaner;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Biljett biljett : biljetter) {
			buf.append(biljett);
			buf.append(" ");
		}

		return buf.toString();
	}

	public int getPrice() {
		int totalpris = 0;

		for (Biljett biljett : biljetter) {
			totalpris += biljett.getPris();
		}

		return totalpris;
	}

	public Dag getSistaGiltighetsdag() {
		Dag sistaDag = null;

		if (biljetter.size() > 0) {
			// Returnera sista biljettens sista giltighetsdag
			Biljett biljett = biljetter.get(biljetter.size() - 1);
			sistaDag = biljett.getSistaGiltighetsdag();
		}

		return sistaDag;
	}

	public void write(PrintStream p) {
		for (Biljett biljett : biljetter) {
			biljett.write(p);
		}
	}
}
