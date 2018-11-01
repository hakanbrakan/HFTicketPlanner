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

	private static final String PROPERTIES_MUST_NOT_BE_NULL = "Properties must not be null";
	private static final String DELBAR = "Delbar";
	private static final String NOLL = "0";
	private static final String PRIS = "Pris";
	private static final String GILTIG_ANTAL_RESOR = "GiltigAntalResor";
	private static final String GILTIG_ANTAL_DAGAR = "GiltigAntalDagar";
	private static final String BILJETTYPER = "biljettTyper";
	private Properties props;

	public Ticketcreator(Properties properties) {
		Asserter.isNotNull(properties, PROPERTIES_MUST_NOT_BE_NULL);
		this.props = properties;
	}

	public List<Biljett> createAllTickets(Resa resa) {
		List<Biljett> lista = new ArrayList<>();
		List<String> biljettTyper = Arrays.asList(props.getProperty(BILJETTYPER).split("\\s*,\\s*"));

		for (String enBiljettyp : biljettTyper) {
			int pris = Integer.parseInt(props.getProperty(enBiljettyp+PRIS, NOLL));
			int giltigAntalDagar = Integer.parseInt(props.getProperty(enBiljettyp+GILTIG_ANTAL_DAGAR, NOLL));
			int giltigAntalResor = Integer.parseInt(props.getProperty(enBiljettyp+GILTIG_ANTAL_RESOR, NOLL));
			Biljett.DELBAR arDelbar = Boolean.parseBoolean(props.getProperty(enBiljettyp+DELBAR)) ? Biljett.DELBAR.JA:Biljett.DELBAR.NEJ;
			Configbiljett enBiljett = Configbiljett.getInstance(enBiljettyp, resa, giltigAntalResor , giltigAntalDagar, pris, arDelbar);
			lista.add(enBiljett);
		}

		return lista;
	}

}
