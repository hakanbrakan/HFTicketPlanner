package se.frihak.ticketplanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import se.frihak.ticketplanner.kalender.Dag;

public class PlannerUtility extends TicketplannerBase {

	public Properties getProperties(InputStream fileAsStream) throws IOException {
		Properties props = new Properties();
		props.load(fileAsStream);
		return props;
	}

	public List<Dag> getDagPlan(Dag firstDay, Dag lastDay) {
		List<Dag> list = new ArrayList<Dag>();
		list.add(firstDay);
		Dag tempDay = firstDay.getNextDag();

		while (tempDay.before(lastDay)) {
			list.add(tempDay);
			tempDay = tempDay.getNextDag();
		}
		list.add(lastDay);

		return list;
	}

	public InputStream getReaderFromFilename(String filename) throws FileNotFoundException {
		return new FileInputStream(filename);
	}
}
