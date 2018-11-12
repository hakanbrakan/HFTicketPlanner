package se.frihak.ticketplanner;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.LogManager;

import org.junit.BeforeClass;
import org.junit.Test;

public class HFTicketplannerTest {

	@BeforeClass
    public static void beforeClass() {
    		System.setProperty("java.util.logging.config.file", ClassLoader.getSystemResource("logging.properties").getPath());
		reReadLogManager();
    }
    
	@Test
	public void testMainFunctionOnFiles_Stor() throws IOException {
		File tempFile = getTempfile();
		
		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20180820.properties").getPath(), tempFile.getPath()});

		final List<String> linesActual = Files.readAllLines(tempFile.toPath());
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20180820.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void testMainFunctionOnFiles_Liten() throws IOException {
		File tempFile = getTempfile();

		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20180827.properties").getPath(), tempFile.getPath()});

		final List<String> linesActual = Files.readAllLines(tempFile.toPath());
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20180827.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void testMainFunctionOnFiles_Gammal() throws IOException {
		File tempFile = getTempfile();

		HFTicketplanner.main(new String[] { getClass().getResource("/HFticketplanner_20150911.properties").getPath(), tempFile.getPath()});

		final List<String> linesActual = Files.readAllLines(tempFile.toPath());
		final List<String> linesExpected = Files.readAllLines(Paths.get(getClass().getResource("/HFTicketplannerReport_20150911.txt").getPath()));
		assertEquals(linesExpected, linesActual);
	}

	@Test
	public void justToImproveCodeCoverage() throws IOException {
		new HFTicketplanner();
	}

	private static void reReadLogManager() {
		LogManager lm = LogManager.getLogManager();
		try {
			lm.readConfiguration();
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getTempfile() throws IOException {
		File tempFile = File.createTempFile("HFTicketplanner-", ".tmp");
		tempFile.deleteOnExit();
		return tempFile;
	}
}
