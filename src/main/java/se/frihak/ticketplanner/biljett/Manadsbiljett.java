package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.Properties;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Manadsbiljett extends Biljett {

	public Manadsbiljett(Resa resa, Properties props) {
		super();
		int pris = Integer.parseInt(props.getProperty("priceManad"));
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
		if (resa.getDag().before(getSistaGiltighetsdag().getNextDag()) && getForstaGiltighetsdag().before(resa.getDag().getNextDag())) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Månadskort " + super.getForstaGiltighetsdag() + " t.o.m " + super.getSistaGiltighetsdag();
	}

	@Override
	public void write(PrintStream p) {
		p.print("M" + getForstaGiltighetsdag() + " ");
		// TODO Skapa en testmetod JUnit
	}

}
