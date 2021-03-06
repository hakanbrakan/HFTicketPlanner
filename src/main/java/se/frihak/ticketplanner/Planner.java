package se.frihak.ticketplanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Planner{
	private static final String RESOR = "resor";
	private static final String DEFAULT_RESOR = "defaultResor";
	private static final String SISTA_RESDAG = "sistaResdag";
	private static final String FORSTA_RESDAG = "forstaResdag";
	private static final Logger LOGGER = Logger.getLogger(Planner.class.getName() );

	private int counter = 0;

	public List<Biljettplan> planeraBiljetter(Properties props) {
		Dag firstDay = new Dag(props.getProperty(FORSTA_RESDAG));
		Dag lastDay = new Dag(props.getProperty(SISTA_RESDAG));

		List<Resa> resor = getReseplan(firstDay, lastDay, props);

		List<Biljettplan> plan = createBiljettplan(resor, new Ticketcreator(props));

		return plan;
	}

	private List<Resa> getReseplan(Dag firstDay, Dag lastDay, Properties props) {
		PlannerUtility util = new PlannerUtility();
		List<Dag> dagPlan = util.getDagPlan(firstDay, lastDay);

		// Skapa alla resor mellan första och sista dag
		List<Resa> reseplan = new ArrayList<>(dagPlan.size());

		for (Dag day : dagPlan) {
			int antalResor = getAntalResor(day, props);

			for (int i = 0; i < antalResor; i++) {
				reseplan.add(new Resa(day));
			}
		}

		return reseplan;
	}

	private int getAntalResor(Dag day, Properties props) {
		String veckodag = day.getWeekdayFormatted();
		String key = DEFAULT_RESOR + veckodag;
		int antalResor = 0;

		if (props.containsKey(key)) {
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}

		key = RESOR + day.toString();

		if (props.containsKey(key)) {
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}

		return antalResor;
	}

	private List<Biljettplan> createBiljettplan(List<Resa> resor, Ticketcreator ticketcreator) {
		List<Biljettplan> plan = new ArrayList<>();
		plan.add(new Biljettplan());

		for (Resa resa : resor) {
			List<Biljettplan> tempLista = new ArrayList<>();
			for (Biljettplan biljettplan : plan) {
				tempLista.addAll(biljettplan.planera(resa, ticketcreator));
			}

			plan.clear();
			plan.addAll(tempLista);

			plan = rensaPlaner(plan, resa.getDag());
		}

		Collections.sort(plan, new PriceComparator());

		return plan;
	}

	private List<Biljettplan> rensaPlaner(List<Biljettplan> plan, Dag planeringsdag) {
		List<Biljettplan> nyLista = new ArrayList<>();
		Dag oldDay = new Dag("1901-01-01");

		Collections.sort(plan, new LastDateComparator());

		for (Biljettplan bp : plan) {
			if (oldDay.equals(bp.getSistaGiltighetsdag()) && planeringsdag.equals(oldDay)) {
				// Samma dag, därför ska vi rensa bort denna
				// Stoppa alltså inte in den i den nya listan
				LOGGER.log(Level.FINER, "{0} Rensar plan: {1}", new Object[] {counter++, bp.toString()});

			} else {
				nyLista.add(bp);
				oldDay = bp.getSistaGiltighetsdag();
			}
		}

		return nyLista;
	}
}
