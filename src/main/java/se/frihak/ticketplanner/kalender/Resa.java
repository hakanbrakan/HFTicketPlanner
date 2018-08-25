package se.frihak.ticketplanner.kalender;

import se.frihak.ticketplanner.TicketplannerBase;

public class Resa extends TicketplannerBase
{
	private Dag resdag = null;

	public Resa(Dag newResdag)
	{
		resdag = newResdag;
	}

	public Dag getDag()
	{
		return resdag;
	}

	public String toString()
	{
		return resdag.toString();
	}
	
	
}
