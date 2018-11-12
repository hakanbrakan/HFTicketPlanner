package se.frihak.ticketplanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.frihak.ticketplanner.biljett.Biljettplan;

public class HFTicketplanner {
	private static final Logger LOGGER = Logger.getLogger(HFTicketplanner.class.getName() );

	public static void main(String[] args) {
		try {
			PlannerUtility util = new PlannerUtility();
			InputStream fileAsStream = util.getReaderFromFilename(args[0]);
			Properties props = util.getProperties(fileAsStream);

			Planner planner = new Planner();
			List<Biljettplan> biljettplaner = planner.planeraBiljetter(props);
			
			String reportfile = props.getProperty("outfile");
			if (args.length > 1) {
				reportfile = args[1];
			}
			LOGGER.log(Level.INFO, "Reportfile: {0}", new Object[] {reportfile});
			util.writePlaner(biljettplaner, reportfile );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
