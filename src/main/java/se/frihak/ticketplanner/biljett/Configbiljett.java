package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.security.InvalidParameterException;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Configbiljett extends Biljett {

	private String namn;
	private int giltigAntalResor;

	private Configbiljett(String biljettnamn, Resa resa, int giltigAntalResor, int giltigAntalDagar, int pris) {
		super();
		this.namn = biljettnamn;
		this.giltigAntalResor = giltigAntalResor;
		setPris(pris);
		setForstaGiltighetsdag(resa.getDag());

		Dag sistaDag = (Dag) resa.getDag().clone();
		sistaDag.addDays(giltigAntalDagar - 1); // Giltighetstiden är inkluderat idag, därför -1 här
		setSistaGiltighetsdag(sistaDag);

		add(resa); //TODO Denna måste tydligen ligga sist. Är det bra
	}

	@Override
	public boolean isValid(Resa resa) {
		return resa.getDag().before(getSistaGiltighetsdag().getNextDag())
				&& getForstaGiltighetsdag().before(resa.getDag().getNextDag())
				&& resor.size() < giltigAntalResor;
	}

	@Override
	public void write(PrintStream p) {
		p.print(namn.substring(0,1) + getForstaGiltighetsdag() + " ");
	}

	@Override
	public String toString() {
		return namn + " " + super.getForstaGiltighetsdag();
	}

	public static Configbiljett getInstance(String biljettnamn, Resa resa, int giltigAntalResor, int giltigAntalDagar, int pris) {
		if(biljettnamn == null) { //TODO borde vara isempty fr stringutils
			throw new InvalidParameterException("namn is null");
		}
		if(resa == null) { //TODO borde vara notnull fr ??
			throw new InvalidParameterException("resa is null");
		}
		if(giltigAntalResor <= 0) {
			throw new InvalidParameterException("giltigAntalResor is below 1");
		}
		if(giltigAntalDagar <= 0) {
			throw new InvalidParameterException("giltigAntalDagar is below 1");
		}
		if(pris < 0) {
			throw new InvalidParameterException("pris is below 0");
		}
		
		return new Configbiljett(biljettnamn, resa, giltigAntalResor, giltigAntalDagar, pris);
	}
}
