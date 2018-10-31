package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;
import se.frihak.ticketplanner.validators.Asserter;
import se.frihak.ticketplanner.validators.Validator;

public class Configbiljett extends Biljett {

	private String namn;
	private int giltigAntalResor;

	private Configbiljett(String biljettnamn, Resa resa, int giltigAntalResor, int giltigAntalDagar, int pris, DELBAR arDelbar) {
		super(arDelbar);
		this.namn = biljettnamn;
		this.giltigAntalResor = giltigAntalResor;
		setPris(pris);
		setForstaGiltighetsdag(resa.getDag());

		Dag sistaDag = resa.getDag().plusDays(giltigAntalDagar - 1); // Giltighetstiden är inkluderat idag, därför -1 här
		setSistaGiltighetsdag(sistaDag);

		add(resa); //TODO Denna måste tydligen ligga sist. Är det bra
	}

	@Override
	public boolean isValid(Resa resa) {
		return resa.getDag().isBefore(getSistaGiltighetsdag().getNextDag())
				&& getForstaGiltighetsdag().isBefore(resa.getDag().getNextDag())
				&& resor.size() < giltigAntalResor;
	}

	@Override
	public void write(PrintStream p) {
		p.print(namn.substring(0,1) + getForstaGiltighetsdag() + " ");
	}

	@Override
	public String toString() {
		if (getForstaGiltighetsdag().isBefore(getSistaGiltighetsdag())) {
			return namn +" " + super.getForstaGiltighetsdag() + " t.o.m " + getSistaGiltighetsdag();
		}
		return namn + " " + super.getForstaGiltighetsdag();
	}

	public static Configbiljett getInstance(String biljettnamn, Resa resa, int giltigAntalResor, int giltigAntalDagar, int pris, DELBAR arDelbar) {
		Asserter.isNotEmpty(biljettnamn, "biljettnamn must not be null or empty");
		Asserter.isNotNull(resa, "resa must not be null");
		Asserter.isNotNull(arDelbar, "delbar must not be null");
		Validator.isTrue(giltigAntalResor > 0, "giltigAntalResor is below 1");
		Validator.isTrue(giltigAntalDagar > 0, "giltigAntalDagar is below 1");
		Validator.isTrue(pris >= 0, "pris is below 0");
		
		return new Configbiljett(biljettnamn, resa, giltigAntalResor, giltigAntalDagar, pris, arDelbar);
	}

	public static Configbiljett getInstance(String biljettnamn, Resa resa, int giltigAntalResor, int giltigAntalDagar, int pris) {
		return getInstance(biljettnamn, resa, giltigAntalResor, giltigAntalDagar, pris, DELBAR.NEJ);
	}
	
	@Override
	public Dag getSistaGiltighetsdag() {
		//TODO Det här var en konstig konstruktion. Verkar som att denna finns bara för att sätta om 
		//sista dag när alla reor är förbrukade så man inte behöver skapa o kasta planer i onödan.
		//TODO gör något åt denna. Tex vid rensa planer inte bara titta på sistagiltighetsdag utan även om bilj är valid på något sätt.
		//Oavsett vilket bör det förbättras.
		Dag sistaDag = null;

		if (resor.size() < giltigAntalResor) {
			sistaDag = super.getSistaGiltighetsdag();
		} else {
			Resa sistaResa = resor.get(resor.size() - 1);
			sistaDag = sistaResa.getDag();
		}

		return sistaDag;
	}

	@Override
	public String getNamn() {
		return namn;
	}
}
