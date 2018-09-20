package se.frihak.ticketplanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import se.frihak.ticketplanner.biljett.Biljettplan;

public class HFTicketplanner {

	public static void main(String[] args) {
		try {
			PlannerUtility util = new PlannerUtility();
			InputStream fileAsStream = util.getReaderFromFilename(args[0]);
			Properties props = util.getProperties(fileAsStream);

			Planner planner = new Planner();
			List<Biljettplan> biljettplaner = planner.planeraBiljetter(props);
			
			String reportfile = props.getProperty("outfile");
			util.writePlaner(biljettplaner, reportfile );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
