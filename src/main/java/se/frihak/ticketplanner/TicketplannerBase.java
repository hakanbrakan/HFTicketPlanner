package se.frihak.ticketplanner;

public class TicketplannerBase
{
//	private static int objektnummer = 0;
	
//	private int objnr = objektnummer++;
	
	

	public TicketplannerBase()
	{
		super();
//		System.out.println("Skapar " + this.getClass().getName() + " nr " + objnr );
	}



	protected void finalize() throws Throwable
	{
		super.finalize();
//		System.out.println("Destruerar" + this.getClass().getName() + " nr " + objnr );
	}

}
