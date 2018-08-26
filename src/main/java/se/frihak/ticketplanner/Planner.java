package se.frihak.ticketplanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

public class Planner extends TicketplannerBase
{

	public void planeraBiljetter(Properties props)
	{
		Dag firstDay = new Dag(props.getProperty("forstaResdag"));
		Dag lastDay =  new Dag(props.getProperty("sistaResdag"));
		
		List<Resa> resor = getReseplan(firstDay, lastDay, props);
		
		List<Biljettplan> plan = createBiljettplan(resor, props);

		write(plan);
	
	}

	private List<Resa> getReseplan(Dag firstDay, Dag lastDay, Properties props)
	{
		
		
		
		// Skapa en testmetod JUnit om den blir public

		
		
		PlannerUtility util = new PlannerUtility();
		List<Dag> dagPlan = util.getDagPlan(firstDay, lastDay);
		
		
		// Skapa alla resor mellan första och sista dag
		List<Resa> reseplan = new ArrayList<Resa>(dagPlan.size());
		
		for (Iterator<Dag> iter = dagPlan.iterator(); iter.hasNext();)
		{
			Dag day = iter.next();
			int antalResor = getAntalResor(day, props);
			
			for (int i = 0; i < antalResor; i++)
			{
				reseplan.add(new Resa(day));
			}
		}
		
		return reseplan;
	}

	private int getAntalResor(Dag day, Properties props)
	{

		
		
		// Skapa en testmetod JUnit om denna blir public

		
		
		String veckodag = day.getWeekdayFormatted();
		String key = "defaultResor" + veckodag;
		int antalResor = 0;
		
		if (props.containsKey(key))
		{
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}
		
		
		key = "resor" + day.toString();
		
		if (props.containsKey(key))
		{
			String strAntalResor = props.getProperty(key);
			antalResor = Integer.parseInt(strAntalResor);
		}

		return antalResor;
	}

	private void write(List<Biljettplan> plan)
	{
		
		
		
		// Skapa en testmetod JUnit om den blir public
		
		FileOutputStream out = null; // declare a file output
		PrintStream p = null; // declare a print stream object
		try
		{
			out = new FileOutputStream("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/Report.txt");
			p = new PrintStream( out );
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		for (Iterator<Biljettplan> iter = plan.iterator(); iter.hasNext();)
		{
			Biljettplan biljplan = iter.next();
			
			System.out.println("Pris: " + biljplan.getPrice() + "     :     " + biljplan);
			p.print ("Pris: " + biljplan.getPrice() + ", ");
			biljplan.write(p);
			p.println();
//			p.println ("Pris: " + biljplan.getPrice() + "     :     " + biljplan);
		}
		
		 p.close();
		 try
		{
			out.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private List<Biljettplan> createBiljettplan(List<Resa> resor, Properties props)
	{
		
		
		
		// Skapa en testmetod JUnit om den blir public
		
		
		
		List<Biljettplan> plan = new ArrayList<Biljettplan>();
		plan.add(new Biljettplan());
		
		for (Iterator<Resa> resorIter = resor.iterator(); resorIter.hasNext();)
		{
			Resa resa = resorIter.next();
			
			Vector<Biljettplan> vec = new Vector<Biljettplan>();
			for (Iterator<Biljettplan> planIter = plan.iterator(); planIter.hasNext();)
			{
				Biljettplan biljettplan = planIter.next();
				
				List<Biljettplan> planArray = biljettplan.planera(resa, props);
				vec.addAll(planArray);
			}

			plan.clear();
			plan.addAll(vec);

			plan = rensaPlaner(plan, resa.getDag());
		}
		
		
		//Sortera alla planer på pris??
		Collections.sort(plan, new PriceComparator());
		

		return plan;
	}

	private List<Biljettplan> rensaPlaner(List<Biljettplan> plan, Dag planeringsdag)
	{
		// Om denna blir public, Skapa en testmetod JUnit
		
		
		//TODO Testa hur rensning sker om man har en tioresor och en månads med samma sistagiltighetsdag.
		
		
		List<Biljettplan> nyLista = new ArrayList<Biljettplan>();
		Dag oldDay = new Dag("1901-01-01");
		
		
		//Sortera alla planer på sista giltighetsdag
		Collections.sort(plan, new LastDateComparator());
		
		Iterator<Biljettplan> iter = plan.iterator();
//		Biljettplan oldPlan = null; //Endast för debugging
		while (iter.hasNext())
		{
			Biljettplan bp = iter.next();
			if(oldDay.equals(bp.getSistaGiltighetsdag()) && planeringsdag.equals(oldDay))
			{
				//Samma dag, därför ska vi rensa bort denna
				//Stoppa alltså inte in den i den nya listan
				System.out.println("Rensar plan: " + bp.toString());
			}
			else
			{
				nyLista.add(bp);
				oldDay = bp.getSistaGiltighetsdag();
//				oldPlan = bp;
			}
		}

		return nyLista;
	}
}
