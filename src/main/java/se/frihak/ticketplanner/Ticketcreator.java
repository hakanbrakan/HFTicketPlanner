package se.frihak.ticketplanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import se.frihak.ticketplanner.biljett.Biljett;
import se.frihak.ticketplanner.biljett.Configbiljett;
import se.frihak.ticketplanner.kalender.Resa;
import se.frihak.ticketplanner.validators.Asserter;

public class Ticketcreator {

	private Properties props;

	public Ticketcreator(Properties properties) {
		Asserter.isNotNull(properties, "Properties must not be null");
		this.props = properties;
	}

	public List<Biljett> createAllTickets(Resa resa) {
		List<Biljett> lista = new ArrayList<Biljett>();
		List<String> biljettTyper = Arrays.asList(props.getProperty("biljettTyper").split("\\s*,\\s*"));

		for (String enBiljettyp : biljettTyper) {
			int pris = Integer.parseInt(props.getProperty(enBiljettyp+"Pris", "0"));
			int giltigAntalDagar = Integer.parseInt(props.getProperty(enBiljettyp+"GiltigAntalDagar", "0"));
			int giltigAntalResor = Integer.parseInt(props.getProperty(enBiljettyp+"GiltigAntalResor", "0"));
			Configbiljett enBiljett = Configbiljett.getInstance(enBiljettyp, resa, giltigAntalResor , giltigAntalDagar, pris);
			lista.add(enBiljett);
		}

		return lista;
	}

}
