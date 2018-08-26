package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class HFTicketplannerTest {
	@Test
	public void testMainFunctionOnFiles() throws IOException {
		HFTicketplanner.main(null);
		
		final List<String> linesActual = Files.readAllLines(Paths.get("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get("/Users/inger/gitRepos/HFTicketPlanner/src/test/resources/HFTicketplannerReport_20180820.txt"));
		assertEquals(linesExpected, linesActual);;
	}

}
