package se.frihak.ticketplanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HFTicketplannerTestNoPrintOnSystemErr {

	private PrintStream err;

	@Before
	public void initiera() {
		err = System.err;
		System.setErr(new PrintStream(new ByteArrayOutputStream()));
	}

	@After
	public void stada() {
		System.setErr(err);
	}

	@Test
	public void improveCodeCoverageWithThrownFileNotFound() {
		HFTicketplanner.main(new String[] { "XXXXXXXXXXXXXXXX" });
	}
}
