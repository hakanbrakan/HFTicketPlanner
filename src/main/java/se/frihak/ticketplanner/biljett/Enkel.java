package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.Properties;

import se.frihak.ticketplanner.kalender.Resa;

public class Enkel extends Biljett
{

	public Enkel(Resa resa, Properties props)
	{
		super();
		int pris = Integer.parseInt(props.getProperty("priceEnkel"));
		setPris(pris);
		setForstaGiltighetsdag(resa.getDag());

		setSistaGiltighetsdag(resa.getDag());
		add(resa);
	}

	public boolean isValid(Resa resa)
	{
		//Har du köpt en enkelresa så kan den aldrig vara valid för ytterligare en resa
		//Därför returnerar vi alltid false här.

		return false;
	}

	public String toString()
	{
		return "Enkel " + super.getForstaGiltighetsdag();
	}

	public void write(PrintStream p)
	{
		p.print("E" + getForstaGiltighetsdag() + " ");
		// TODO Skapa en testmetod JUnit
		
	}
}
