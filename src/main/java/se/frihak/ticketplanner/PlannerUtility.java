package se.frihak.ticketplanner;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import se.frihak.ticketplanner.kalender.Dag;

public class PlannerUtility extends TicketplannerBase
{

	public Properties getProperties()
	{
		Properties props = new Properties();
		try
		{
//			props.load(new FileInputStream("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFticketplanner_20150911.properties"));
			props.load(new FileInputStream("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFticketplanner_20180820.properties"));
		}
		catch (Exception e)
		{
			// Filen fanns inte. Så vi stoppar in några defaultvärden
			props.setProperty("forstaResdag", "2005-01-01");
			props.setProperty("sistaResdag", "2005-01-02");
		}
		return props;
	}

	public List<Dag> getDagPlan(Dag firstDay, Dag lastDay)
	{
		List<Dag> list = new ArrayList<Dag>();
		list.add(firstDay);
		Dag tempDay = firstDay.getNextDag();
		
		while (tempDay.before(lastDay))
		{
			list.add(tempDay);
			tempDay = tempDay.getNextDag();
		}
		list.add(lastDay);

		return list;
	}
}
