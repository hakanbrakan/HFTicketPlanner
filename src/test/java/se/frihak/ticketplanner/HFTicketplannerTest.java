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
		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20180820.properties").getPath() });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20180820.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void testMainFunctionOnFiles_Liten() throws IOException {
		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20180827.properties").getPath() });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20180827.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void testMainFunctionOnFiles_Gammal() throws IOException {
		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20150911.properties").getPath() });

		final List<String> linesActual = Files.readAllLines(Paths.get("/private/tmp/Report.txt"));
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20150911.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void improveCodeCoverageWithThrownFileNotFound() throws IOException {
		HFTicketplanner.main(new String[] { "XXXXXXXXXXXXXXXX" });
	}

	@Test
	public void justToImproveCodeCoverage() throws IOException {
		new HFTicketplanner();
	}
}
