package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class PriceComparatorTest {
	private Biljettplan plan;
	private Properties props;

	@Before
	public void setUp() throws Exception {
		props = new Properties();
		props.setProperty("priceEnkel", "90");
		props.setProperty("priceTioresor", "650");
		props.setProperty("priceManad", "1900");
	}

	@Test
	public void testCompare() {
		plan = new Biljettplan();
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		List<Biljettplan> list = plan.planera(resa, props);

		Biljettplan planMedMankort = list.get(0);
		Biljettplan planMedEnkel = list.get(1);
		Biljettplan planMedTioResor = list.get(2);

		PriceComparator comp = new PriceComparator();
		assertEquals(-1, comp.compare(planMedEnkel, planMedMankort));
		assertEquals(1, comp.compare(planMedTioResor, planMedEnkel));
		assertEquals(0, comp.compare(planMedEnkel, planMedEnkel));
	}
}
