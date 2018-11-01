package se.frihak.ticketplanner.kalender;

public class Resa {
	private Dag resdag = null;

	public Resa(Dag newResdag) {
		resdag = newResdag;
	}

	public Dag getDag() {
		return resdag;
	}

	@Override
	public String toString() {
		return resdag.toString();
	}
}
