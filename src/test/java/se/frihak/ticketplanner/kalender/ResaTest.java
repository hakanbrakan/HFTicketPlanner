package se.frihak.ticketplanner.kalender;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResaTest {
	@Test
	public void testGetDag() {
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		Dag dag2 = resa.getDag();
		assertEquals(dag2.toString(), dag.toString());
	}

	@Test
	public void testToString() {
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		String dagString = resa.toString();
		assertEquals("2007-11-01", dagString);
	}
}
