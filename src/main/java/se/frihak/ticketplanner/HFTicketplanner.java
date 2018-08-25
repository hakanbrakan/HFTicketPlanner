package se.frihak.ticketplanner;

import java.util.Properties;

public class HFTicketplanner
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PlannerUtility util = new PlannerUtility();
		Properties props = util.getProperties();
//		System.out.println(props);

		Planner planner = new Planner();
		planner.planeraBiljetter(props);

	}
}
