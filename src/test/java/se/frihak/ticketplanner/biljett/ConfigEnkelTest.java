package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class ConfigEnkelTest {

	private Dag dag;
	private Resa resa;
	private Configbiljett enkel;

	@Before
	public void setUp() {
		dag = new Dag("2007-11-12");
		resa = new Resa(dag);
		enkel = Configbiljett.getInstance("Enkel", resa, 1, 1, 123);
	}

	@Test
	public void testEnkelIsValid() {
		assertFalse(enkel.isValid(resa));

		Resa resa2 = new Resa(new Dag("2007-11-13"));
		assertFalse(enkel.isValid(resa2));

		Resa resa3 = new Resa(new Dag("2007-11-11"));
		assertFalse(enkel.isValid(resa3));
	}

	@Test
	public void testEnkelToString() {
		assertEquals("Enkel 2007-11-12", enkel.toString());
	}
}
