package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class BiljettplanTest {
	private Biljettplan plan;
	private Properties props;

	@Before
	public void setUp() throws Exception {
		plan = new Biljettplan();

		props = new Properties();
		props.setProperty("priceEnkel", "90");
		props.setProperty("priceTioresor", "650");
		props.setProperty("priceManad", "1900");
	}

	@Test
	public void testPlanera() {
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		List<Biljettplan> list = plan.planera(resa, props);

		assertEquals(3, list.size());
		assertEquals("[Månadskort 2007-11-01 t.o.m 2007-11-30 , Enkel 2007-11-01 , Tioresor 2007-11-01 t.o.m 2007-11-30 ]", list.toString());
	}

	@Test
	public void testToString() {
		assertEquals("", plan.toString());

		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		List<Biljettplan> list = plan.planera(resa, props);
		assertEquals("[Månadskort 2007-11-01 t.o.m 2007-11-30 , Enkel 2007-11-01 , Tioresor 2007-11-01 t.o.m 2007-11-30 ]", list.toString());

		dag = new Dag("2007-11-02");
		resa = new Resa(dag);
		plan = list.get(0);
		list = plan.planera(resa, props);
		assertEquals("[Månadskort 2007-11-01 t.o.m 2007-11-30 ]", list.toString());

	}

	@Test
	public void testGetPrice() {
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		List<Biljettplan> list = plan.planera(resa, props);

		Biljettplan plan = list.get(0);
		assertEquals(1900, plan.getPrice());

		plan = list.get(1);
		assertEquals(90, plan.getPrice());

		plan = list.get(2);
		assertEquals(650, plan.getPrice());

		// TODO Fler tester med st�rre planer (fler biljetter) h�r
	}

	@Test
	public void testGetSistaGiltighetsdag() {
		assertNull(plan.getSistaGiltighetsdag());

		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);

		Dag sistaDag = plan.getSistaGiltighetsdag();
		assertNull(sistaDag);

		List<Biljettplan> list = plan.planera(resa, props);

		Biljettplan plan2 = list.get(0);
		assertEquals("2007-11-30", plan2.getSistaGiltighetsdag().toString());

		plan2 = list.get(1);
		assertEquals("2007-11-01", plan2.getSistaGiltighetsdag().toString());

		plan2 = list.get(2);
		assertEquals("2007-11-30", plan2.getSistaGiltighetsdag().toString());
	}
}