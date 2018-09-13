package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.biljett.Biljett;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class TicketcreatorTest {

	private Resa resa;

	@Before
	public void setUp() throws Exception {
		resa = new Resa(new Dag("2007-11-01"));
	}

	@Test
	public void testCreateEnkel() {
		Ticketcreator creator = new Ticketcreator(createEnkelProperties());
		List<Biljett> allaBiljetter = creator.createAllTickets(resa);
		assertEquals(1, allaBiljetter.size());
		assertEquals("2007-11-01", allaBiljetter.get(0).getForstaGiltighetsdag().toString());
		assertEquals("2007-11-01", allaBiljetter.get(0).getSistaGiltighetsdag().toString());
		assertEquals(1234, allaBiljetter.get(0).getPris());
	}

	@Test
	public void testCreateTreTyper() {
		Ticketcreator creator = new Ticketcreator(createTreTyperProperties());
		List<Biljett> allaBiljetter = creator.createAllTickets(resa);
		assertEquals(3, allaBiljetter.size());
		assertEquals("2007-11-01", allaBiljetter.get(0).getForstaGiltighetsdag().toString());
		assertEquals(1234, allaBiljetter.get(1).getPris());
		assertEquals(2450, allaBiljetter.get(2).getPris());
	}

	private Properties createEnkelProperties() {
		Properties props = new Properties();
		
		props.setProperty("biljettTyper", "Enkel");
		props.setProperty("EnkelPris", "1234");
		props.setProperty("EnkelGiltigAntalDagar", "1");
		props.setProperty("EnkelGiltigAntalResor", "1");
		
		return props;
	}

	private Properties createTreTyperProperties() {
		Properties props = new Properties();
		
		props.setProperty("biljettTyper", "Enkel,TioResor,Manad");
		props.setProperty("EnkelPris", "100");
		props.setProperty("EnkelGiltigAntalDagar", "1");
		props.setProperty("EnkelGiltigAntalResor", "1");
		props.setProperty("TioResorPris", "1234");
		props.setProperty("TioResorGiltigAntalDagar", "90");
		props.setProperty("TioResorGiltigAntalResor", "10");
		props.setProperty("ManadPris", "2450");
		props.setProperty("ManadGiltigAntalDagar", "30");
		props.setProperty("ManadGiltigAntalResor", "48");
		
		return props;
	}
	
}
