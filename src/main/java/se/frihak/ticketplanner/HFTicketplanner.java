package se.frihak.ticketplanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HFTicketplanner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PlannerUtility util = new PlannerUtility();
			InputStream fileAsStream = util.getReaderFromFilename(args[0]);
			Properties props = util.getProperties(fileAsStream);

			Planner planner = new Planner();
			planner.planeraBiljetter(props);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
