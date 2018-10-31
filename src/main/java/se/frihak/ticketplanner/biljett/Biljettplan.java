package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import se.frihak.ticketplanner.Ticketcreator;
import se.frihak.ticketplanner.TicketplannerBase;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Biljettplan extends TicketplannerBase {
	private List<Biljett> biljetter = new ArrayList<>();

	public Biljettplan(Biljettplan biljettplan, Biljett bilj) {
		biljetter.addAll(biljettplan.biljetter);
		biljetter.add(bilj);
	}

	public Biljettplan() {
	}

	public List<Biljettplan> planera(Resa resa, Ticketcreator ticketcreator) {
		List<Biljettplan> nyaPlaner = new ArrayList<>();

		Optional<Biljett> eventuellBiljett = biljetter.stream().filter(biljett -> biljett.isValid(resa) && biljett.isDelbar() == Biljett.DELBAR.NEJ).findFirst();
		if (eventuellBiljett.isPresent()) {
			eventuellBiljett.get().add(resa);
			nyaPlaner.add(this);
		} else {
			Optional<Biljett> evBiljett = biljetter.stream().filter(biljett -> biljett.isValid(resa) && biljett.isDelbar() == Biljett.DELBAR.JA).findFirst();
			if (evBiljett.isPresent()) {
				evBiljett.get().add(resa);
				nyaPlaner.add(this);

				for (Biljett oneTicket : ticketcreator.createAllTickets(resa)) {
					if( ! evBiljett.get().getNamn().equals(oneTicket.getNamn())) {
						nyaPlaner.add(new Biljettplan(this, oneTicket));
					}
				}
			} else {
				// Skapa nya planer, en för varje biljetttyp
				for (Biljett oneTicket : ticketcreator.createAllTickets(resa)) {
					nyaPlaner.add(new Biljettplan(this, oneTicket));
				}
			}
		}

		return nyaPlaner;
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
			Biljett sistaBiljetten = biljetter.get(biljetter.size() - 1);
			sistaDag = sistaBiljetten.getSistaGiltighetsdag();
		}

		return sistaDag;
	}

	public void write(PrintStream p) {
		for (Biljett biljett : biljetter) {
			biljett.write(p);
		}
	}
}
