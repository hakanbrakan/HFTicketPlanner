package se.frihak.ticketplanner.biljett;

import java.io.PrintStream;
import java.util.ArrayList;

import se.frihak.ticketplanner.kalender.Dag;
import se.frihak.ticketplanner.kalender.Resa;

abstract public class Biljett {
	private int pris = 0;
	private Dag forstaGiltighetsdag = null;
	private Dag sistaGiltighetsdag = null;

	abstract public boolean isValid(Resa resa);

	protected ArrayList<Resa> resor = null;
	private DELBAR arDelbar = DELBAR.NEJ;

	public Biljett(DELBAR delbar) {
		this.arDelbar = delbar;
		resor = new ArrayList<>();
	}

	public void setPris(int i) {
		pris = i;
	}

	public Dag getForstaGiltighetsdag() {
		return forstaGiltighetsdag;
	}

	public void setForstaGiltighetsdag(Dag forstaGiltighetsdag) {
		this.forstaGiltighetsdag = forstaGiltighetsdag;
	}

	public Dag getSistaGiltighetsdag() {
		return sistaGiltighetsdag;
	}

	public void setSistaGiltighetsdag(Dag sistaGiltighetsdag) {
		this.sistaGiltighetsdag = sistaGiltighetsdag;
	}

	public boolean add(Resa resa) {
		if (isValid(resa)) {
			resor.add(resa);
			return true;
		}

		return false;
	}

	public int getPris() {
		return pris;
	}

	abstract public void write(PrintStream p);
	abstract public String getNamn();

	public DELBAR isDelbar() {
		return arDelbar ;
	}
	
	public enum DELBAR {
		JA,
		NEJ
	}
}
