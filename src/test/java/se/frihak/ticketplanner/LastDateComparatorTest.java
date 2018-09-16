package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class LastDateComparatorTest {
	private Biljettplan plan;
	private Properties props;
	private Ticketcreator ticketcreator;

	@Before
	public void setUp() throws Exception {
		props = new Properties();
		props.setProperty("biljettTyper", "Manad,Enkel,Tioresor");
		props.setProperty("EnkelPris", "90");
		props.setProperty("TioresorPris", "650");
		props.setProperty("ManadPris", "1900");
		props.setProperty("EnkelGiltigAntalDagar", "1");
		props.setProperty("EnkelGiltigAntalResor", "1");
		props.setProperty("TioresorGiltigAntalDagar", "30");
		props.setProperty("TioresorGiltigAntalResor", "10");
		props.setProperty("ManadGiltigAntalDagar", "30");
		props.setProperty("ManadGiltigAntalResor", "48");
		ticketcreator = new Ticketcreator(props);
	}

	@Test
	public void testCompare() {
		plan = new Biljettplan();
		Dag dag = new Dag("2007-11-01");
		Resa resa = new Resa(dag);
		List<Biljettplan> list = plan.planera(resa, ticketcreator);

		Biljettplan planMedMankort = list.get(0);
		Biljettplan planMedEnkel = list.get(1);
		Biljettplan planMedTioResor = list.get(2);

		LastDateComparator comp = new LastDateComparator();
		assertEquals(-1, comp.compare(planMedEnkel, planMedMankort));
		assertEquals(1, comp.compare(planMedTioResor, planMedEnkel));
		assertEquals(0, comp.compare(planMedMankort, planMedMankort));
		assertEquals(-1, comp.compare(planMedTioResor, planMedMankort));

		dag = new Dag("2007-11-02");
		resa = new Resa(dag);
		list = planMedMankort.planera(resa, ticketcreator);
		planMedMankort = list.get(0);
		assertEquals(-1, comp.compare(planMedTioResor, planMedMankort));
	}
}
