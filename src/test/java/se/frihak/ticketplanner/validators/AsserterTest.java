package se.frihak.ticketplanner.validators;

import org.junit.Test;

public class AsserterTest {

	@Test
	public void createAsserter() {
		new Asserter();
	}

	@Test
	public void passingValidation() {
		Asserter.isNotEmpty("asdf", "This should pass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringIsNull() {
		Asserter.isNotEmpty(null, "This should fail");
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringIsEmpty() {
		Asserter.isNotEmpty("", "This should fail");
	}

	@Test
	public void isNotNull() {
		Asserter.isNotNull("asdf", "This should pass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void isNull() {
		Asserter.isNotNull(null, "This should fail");
	}
}
