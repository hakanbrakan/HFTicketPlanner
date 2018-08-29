package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class HFTicketplannerTest {
	@Test
	public void testMainFunctionOnFiles_Stor() throws IOException {
		HFTicketplanner.main(new String[] { "/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFticketplanner_20180820.properties" });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFTicketplannerReport_20180820.txt"));
		assertEquals(linesExpected, linesActual);
		;
	}

	@Test
	public void testMainFunctionOnFiles_Liten() throws IOException {
		HFTicketplanner.main(new String[] { "/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFticketplanner_20180827.properties" });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFTicketplannerReport_20180827.txt"));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void testMainFunctionOnFiles_Gammal() throws IOException {
		HFTicketplanner.main(new String[] { "/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFticketplanner_20150911.properties" });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFTicketplannerReport_20150911.txt"));
		assertEquals(linesExpected, linesActual);
	}
}
