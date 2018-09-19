package se.frihak.ticketplanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Planner extends TicketplannerBase {

	private int counter = 0;

	public List<Biljettplan> planeraBiljetter(Properties props) {
		Dag firstDay = new Dag(props.getProperty("forstaResdag"));
		Dag lastDay = new Dag(props.getProperty("sistaResdag"));

		List<Resa> resor = getReseplan(firstDay, lastDay, props);

		List<Biljettplan> plan = createBiljettplan(resor, new Ticketcreator(props));

		return plan;
	}

	private List<Resa> getReseplan(Dag firstDay, Dag lastDay, Properties props) {

		// Skapa en testmetod JUnit om den blir public

		PlannerUtility util = new PlannerUtility();
		List<Dag> dagPlan = util.getDagPlan(firstDay, lastDay);

		// Skapa alla resor mellan första och sista dag
		List<Resa> reseplan = new ArrayList<Resa>(dagPlan.size());

		for (Dag day : dagPlan) {
			int antalResor = getAntalResor(day, props);

			for (int i = 0; i < antalResor; i++) {
				reseplan.add(new Resa(day));
			}
		}

		return reseplan;
	}

	private int getAntalResor(Dag day, Properties props) {

		// Skapa en testmetod JUnit om denna blir public

		String veckodag = day.getWeekdayFormatted();
		String key = "defaultResor" + veckodag;
		int antalResor = 0;

		if (props.containsKey(key)) {
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}

		key = "resor" + day.toString();

		if (props.containsKey(key)) {
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}

		return antalResor;
	}

	private List<Biljettplan> createBiljettplan(List<Resa> resor, Ticketcreator ticketcreator) {

		// Skapa en testmetod JUnit om den blir public

		List<Biljettplan> plan = new ArrayList<Biljettplan>();
		plan.add(new Biljettplan());

		for (Resa resa : resor) {
			Vector<Biljettplan> vec = new Vector<Biljettplan>();
			for (Biljettplan biljettplan : plan) {
				List<Biljettplan> planArray = biljettplan.planera(resa, ticketcreator);
				vec.addAll(planArray);
			}

			plan.clear();
			plan.addAll(vec);

			plan = rensaPlaner(plan, resa.getDag());
		}

		// Sortera alla planer på pris??
		Collections.sort(plan, new PriceComparator());

		return plan;
	}

	private List<Biljettplan> rensaPlaner(List<Biljettplan> plan, Dag planeringsdag) {
		// Om denna blir public, Skapa en testmetod JUnit

		// TODO Testa hur rensning sker om man har en tioresor och en månads med samma
		// sistagiltighetsdag.

		List<Biljettplan> nyLista = new ArrayList<Biljettplan>();
		Dag oldDay = new Dag("1901-01-01");

		// Sortera alla planer på sista giltighetsdag
		Collections.sort(plan, new LastDateComparator());

		for (Biljettplan bp : plan) {
			if (oldDay.equals(bp.getSistaGiltighetsdag()) && planeringsdag.equals(oldDay)) {
				// Samma dag, därför ska vi rensa bort denna
				// Stoppa alltså inte in den i den nya listan
				System.out.println(counter  ++ + " Rensar plan: " + bp.toString());

			} else {
				nyLista.add(bp);
				oldDay = bp.getSistaGiltighetsdag();
			}
		}

		return nyLista;
	}
}
