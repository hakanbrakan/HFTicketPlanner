package se.frihak.ticketplanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import se.frihak.ticketplanner.biljett.Biljettplan;
import se.frihak.ticketplanner.kalender.Dag;

public class PlannerUtility {

	private static final String PRIS = "Pris: ";

	public Properties getProperties(InputStream fileAsStream) throws IOException {
		Properties props = new Properties();
		props.load(fileAsStream);
		return props;
	}

	public List<Dag> getDagPlan(Dag firstDay, Dag lastDay) {
		List<Dag> list = new ArrayList<>();
		list.add(firstDay);
		Dag tempDay = firstDay.getNextDag();

		while (tempDay.isBefore(lastDay)) {
			list.add(tempDay);
			tempDay = tempDay.getNextDag();
		}
		list.add(lastDay);

		return list;
	}

	public InputStream getReaderFromFilename(String filename) throws FileNotFoundException {
		return new FileInputStream(filename);
	}

	public void writePlaner(List<Biljettplan> biljettplaner, String reportfile) throws IOException {
		FileOutputStream out = new FileOutputStream(reportfile);
		PrintStream p = new PrintStream(out);

		for (Biljettplan biljplan : biljettplaner) {
			p.print(PRIS + biljplan.getPrice() + ", ");
			biljplan.write(p);
			p.println();
		}

		p.close();
		out.close();
	}
}
