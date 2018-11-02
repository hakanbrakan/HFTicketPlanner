package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class BiljettTest {
	private Dag dag;
	private Resa resa;
	private Biljett biljEnkel1;
	private Biljett biljManad1;
	private Biljett biljTio1;
	private Properties props;

	@Before
	public void setUp() throws Exception {
		dag = new Dag("2007-01-01");
		resa = new Resa(dag);
		props = new Properties();
		props.setProperty("priceEnkel", "90");
		props.setProperty("priceTioresor", "650");
		props.setProperty("priceManad", "1900");

		biljEnkel1 = Configbiljett.getInstance("Enkel", resa, 1, 1, 90);
		biljManad1 = Configbiljett.getInstance("Månadskort", resa, 48, 30, 1900);
		biljTio1 = Configbiljett.getInstance("TioResor", resa, 10, 30, 650);
	}

	@Test
	public void testSetPris() {
		biljEnkel1.setPris(100);
		assertEquals(100, biljEnkel1.getPris());

		biljManad1.setPris(300);
		assertEquals(300, biljManad1.getPris());

		biljTio1.setPris(200);
		assertEquals(200, biljTio1.getPris());
	}

	@Test
	public void testGetPris() {
		biljEnkel1.setPris(100);
		assertEquals(100, biljEnkel1.getPris());

		biljManad1.setPris(300);
		assertEquals(300, biljManad1.getPris());

		biljTio1.setPris(200);
		assertEquals(200, biljTio1.getPris());
	}

	@Test
	public void testGetForstaGiltighetsdag() {
		assertEquals("2007-01-01", biljManad1.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testSetForstaGiltighetsdag() {
		biljManad1.setForstaGiltighetsdag(new Dag("2007-12-12"));
		assertEquals("2007-12-12", biljManad1.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testGetSistaGiltighetsdag() {
		assertEquals(" --- Enkel", "2007-01-01", biljEnkel1.getSistaGiltighetsdag().toString());
		assertEquals(" --- Tio", "2007-01-30", biljTio1.getSistaGiltighetsdag().toString());
		assertEquals(" --- M�nad", "2007-01-30", biljManad1.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testSetSistaGiltighetsdag() {
		biljManad1.setSistaGiltighetsdag(new Dag("2007-12-12"));
		assertEquals("2007-12-12", biljManad1.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testAdd() {
		assertTrue(biljManad1.add(new Resa(new Dag("2007-01-02"))));
		assertEquals("2007-01-30", biljManad1.getSistaGiltighetsdag().toString());

		assertTrue(biljManad1.add(new Resa(new Dag("2007-01-02"))));
		assertEquals("2007-01-30", biljManad1.getSistaGiltighetsdag().toString());

		assertTrue(biljManad1.add(new Resa(new Dag("2007-01-03"))));
		assertEquals("2007-01-30", biljManad1.getSistaGiltighetsdag().toString());

		assertTrue(biljManad1.add(new Resa(new Dag("2007-01-03"))));
		assertEquals("2007-01-30", biljManad1.getSistaGiltighetsdag().toString());

		assertEquals("Månadskort 2007-01-01 t.o.m 2007-01-30", biljManad1.toString());

		assertFalse(biljManad1.add(new Resa(new Dag("2007-01-31"))));
		assertEquals(5, biljManad1.resor.size());
	}
	
	@Test
	public void justForSuperficialCodeCoverage() {
		Biljett.DELBAR.values();
		Biljett.DELBAR.valueOf("JA");
		Biljett.DELBAR.valueOf("NEJ");
	}
}
