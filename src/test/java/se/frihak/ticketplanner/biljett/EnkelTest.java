package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class EnkelTest {
	private Enkel enkel;
	private Resa resa;
	private Dag dag;
	private Properties props;

	@Before
	public void setUp() throws Exception {
		dag = new Dag("2007-11-12");
		resa = new Resa(dag);
		props = new Properties();
		props.setProperty("priceEnkel", "90");
		enkel = new Enkel(resa, props);
	}

	@Test
	public void testIsValid() {
		assertFalse(enkel.isValid(resa));

		Dag dag2 = new Dag("2007-11-13");
		Resa resa2 = new Resa(dag2);
		assertFalse(enkel.isValid(resa2));

		Dag dag3 = new Dag("2007-11-11");
		Resa resa3 = new Resa(dag3);
		assertFalse(enkel.isValid(resa3));
	}

	@Test
	public void testToString() {
		assertEquals("Enkel 2007-11-12", enkel.toString());
	}
}
