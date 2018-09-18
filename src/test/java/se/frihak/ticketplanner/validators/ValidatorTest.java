package se.frihak.ticketplanner.validators;

import org.junit.Test;

public class ValidatorTest {

	@Test
	public void createValidator() {
		new Validator();
	}

	@Test
	public void passingValidation() {
		Validator.isTrue(true, "This should pass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void failingValidation() {
		Validator.isTrue(false, "This should throw");
	}
}
