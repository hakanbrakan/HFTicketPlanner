package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.Properties;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class TioResor extends Biljett {

	@Override
	public Dag getSistaGiltighetsdag() {
		Dag sistaDag = null;

		if (resor.size() < 10) {
			sistaDag = super.getSistaGiltighetsdag();
		} else {
			// Hämta sista resan
			Resa sistaResa = resor.get(resor.size() - 1);

			sistaDag = sistaResa.getDag();
		}

		return sistaDag;
	}

	public TioResor(Resa resa, Properties props) {
		super();
		int pris = Integer.parseInt(props.getProperty("priceTioresor"));
		setPris(pris);
		setForstaGiltighetsdag(resa.getDag());
		Dag sistaDag = null;
		sistaDag = (Dag) resa.getDag().clone();
		sistaDag.addDays(29); // Giltighetstiden är 30dgr inkluderat idag, därför 29 här
		setSistaGiltighetsdag(sistaDag);
		add(resa);

	}

	@Override
	public boolean isValid(Resa resa) {
		boolean result = false;

		if (resor.size() < 10 && !getSistaGiltighetsdag().before(resa.getDag())) {
			result = true;
		}

		return result;
	}

	@Override
	public String toString() {
		return "Tioresor " + super.getForstaGiltighetsdag() + " t.o.m " + getSistaGiltighetsdag();
	}

	@Override
	public void write(PrintStream p) {
		p.print("T" + getForstaGiltighetsdag() + " ");
		// TODO Skapa en testmetod JUnit
	}
}
