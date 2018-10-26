package se.frihak.ticketplanner.biljett;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import se.frihak.ticketplanner.Ticketcreator;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class BiljettplanMedDelbaraBiljetterTest {
	private Biljettplan plan;
	private Properties props;
	private Ticketcreator ticketcreator;
	private Resa resa20071102;
	private Resa resa20071101;

	@Before
	public void setUp() throws Exception {
		plan = new Biljettplan();

		props = new Properties();
		props.setProperty("biljettTyper", "Månadskort,Enkel,Tioresor");
		props.setProperty("EnkelPris", "90");
		props.setProperty("TioresorPris", "650");
		props.setProperty("MånadskortPris", "1900");
		props.setProperty("EnkelGiltigAntalDagar", "1");
		props.setProperty("EnkelGiltigAntalResor", "1");
		props.setProperty("TioresorGiltigAntalDagar", "30");
		props.setProperty("TioresorGiltigAntalResor", "10");
		props.setProperty("MånadskortGiltigAntalDagar", "30");
		props.setProperty("MånadskortGiltigAntalResor", "48");
		ticketcreator = new Ticketcreator(props);
		resa20071101 = new Resa(new Dag("2007-11-01"));
		resa20071102 = new Resa(new Dag("2007-11-02"));
	}

	@Test
	public void planeraMedIckeDelbarTioResor() {
		Biljettplan nyPlan = skapaPlanMedPåbörjadTioResor();

		List<Biljettplan> plan2 = nyPlan.planera(resa20071102, ticketcreator);

		assertEquals("[Tioresor 2007-11-01 t.o.m 2007-11-30 ]", plan2.toString());
	}

	@Test
	public void planeraGiltigDelbarTioResor() {
		props.setProperty("TioresorDelbar", "true");
		Biljettplan nyPlan = skapaPlanMedPåbörjadTioResor();

		List<Biljettplan> planUt = nyPlan.planera(resa20071102, ticketcreator);

		assertEquals(3, planUt.size());
		assertEquals("[Tioresor 2007-11-01 t.o.m 2007-11-30 , Tioresor 2007-11-01 t.o.m 2007-11-30 Månadskort 2007-11-02 t.o.m 2007-12-01 , Tioresor 2007-11-01 t.o.m 2007-11-30 Enkel 2007-11-02 ]", planUt.toString());
	}
	
	@Test
	public void planeraGiltigDelbarTioOchGiltigManad() {
		props.setProperty("TioresorDelbar", "true");
		Biljettplan tioOchManad = skapaPlanMedTioOchManad();

		List<Biljettplan> resultatplaner = tioOchManad.planera(new Resa(new Dag("2007-11-03")), ticketcreator);

		assertEquals(1, resultatplaner.size());
		assertEquals("Tioresor 2007-11-01 t.o.m 2007-11-30 Månadskort 2007-11-02 t.o.m 2007-12-01 ", resultatplaner.get(0).toString());
	}

	@Test
	public void planeraGiltigDelbarTioOchForbrukadManad() {
		props.setProperty("TioresorGiltigAntalDagar", "90");
		props.setProperty("TioresorDelbar", "true");
		Biljettplan tioOchManad = skapaPlanMedTioOchManad();
		
		List<Biljettplan> resultatplaner = tioOchManad.planera(new Resa(new Dag("2007-12-02")), ticketcreator);
		
		assertEquals(3, resultatplaner.size());
		assertEquals("Tioresor 2007-11-01 t.o.m 2008-01-29 Månadskort 2007-11-02 t.o.m 2007-12-01 ", resultatplaner.get(0).toString());
		assertEquals("Tioresor 2007-11-01 t.o.m 2008-01-29 Månadskort 2007-11-02 t.o.m 2007-12-01 Månadskort 2007-12-02 t.o.m 2007-12-31 ", resultatplaner.get(1).toString());
		assertEquals("Tioresor 2007-11-01 t.o.m 2008-01-29 Månadskort 2007-11-02 t.o.m 2007-12-01 Enkel 2007-12-02 ", resultatplaner.get(2).toString());
	}
	
	private Biljettplan skapaPlanMedTioOchManad() {
		Biljettplan nyPlan = skapaPlanMedPåbörjadTioResor();
		List<Biljettplan> planUt = nyPlan.planera(resa20071102, ticketcreator);
		assertEquals(3, planUt.size());
		Biljettplan tioOchManad = planUt.get(1);
		return tioOchManad;
	}
	
	private Biljettplan skapaPlanMedPåbörjadTioResor() {
		return plan.planera(resa20071101, ticketcreator).get(2);
	}
}
