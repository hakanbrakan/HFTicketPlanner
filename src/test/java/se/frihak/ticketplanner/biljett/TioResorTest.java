package se.frihak.ticketplanner.biljett;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class TioResorTest
{
	private TioResor tioResor;
	private Resa resa;
	private Dag dag;
	private Properties props;

	@Before
	public void setUp() throws Exception
	{
		dag = new Dag("2007-11-12");
		resa = new Resa(dag);
		props = new Properties();
		props.setProperty("priceTioresor", "650");
		tioResor = new TioResor(resa, props);
	}


	@Test
	public void testIsValid()
	{
		Dag dag2 = new Dag("2007-11-13");
		Resa resa2 = new Resa(dag2);
		assertTrue(tioResor.isValid(resa2));

		Dag dag3 = new Dag("2008-11-11");
		Resa resa3 = new Resa(dag3);
		assertFalse(tioResor.isValid(resa3));

	}

	@Test
	public void testToString()
	{
		assertEquals("Tioresor 2007-11-12 t.o.m 2007-12-11", tioResor.toString());
	}

	@Test
	public void testSetPris()
	{
		tioResor.setPris(1234);
		assertEquals(1234, tioResor.getPris());
	}

	@Test
	public void testGetForstaGiltighetsdag()
	{
		assertEquals("2007-11-12", tioResor.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testSetForstaGiltighetsdag()
	{
		tioResor.setForstaGiltighetsdag(new Dag("2007-11-11"));
		assertEquals("2007-11-11", tioResor.getForstaGiltighetsdag().toString());
	}

	@Test
	public void testGetSistaGiltighetsdag()
	{
		dag = new Dag("2007-11-12");
		assertEquals("2007-12-11", tioResor.getSistaGiltighetsdag().toString());
		
		//Ytterligare test d� jag tror att jag r�knade fel f�rut
		
		Dag dag3 = new Dag("2007-11-19");
		Resa resa3 = new Resa(dag3);
		Properties props3 = new Properties();
		props3.setProperty("priceTioresor", "650");
		TioResor tioResor3 = new TioResor(resa3, props);
		assertEquals("2007-12-18", tioResor3.getSistaGiltighetsdag().toString());


		
		// Sista giltdag m�ste �ndras n�r man anv�nt alla 10 resor
		Dag dag2 = new Dag("2007-11-12");
		Resa resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-13");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-13");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-14");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-14");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-15");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-15");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		dag2 = new Dag("2007-11-16");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		assertEquals("2007-12-11", tioResor.getSistaGiltighetsdag().toString());
		dag2 = new Dag("2007-11-16");
		resa2 = new Resa(dag2);
		tioResor.add(resa2);
		assertEquals("2007-11-16", tioResor.getSistaGiltighetsdag().toString());

	}

	@Test
	public void testSetSistaGiltighetsdag()
	{
		Dag dag2 = new Dag("2007-12-27");
		tioResor.setSistaGiltighetsdag(dag2);
		assertEquals("2007-12-27", tioResor.getSistaGiltighetsdag().toString());
	}

	@Test
	public void testAdd()
	{
		//TODO fail("Not yet implemented");
	}

	@Test
	public void testGetPris()
	{
		assertEquals(650, tioResor.getPris());
	}
}
