package se.frihak.ticketplanner.validators;

public class Validator {

	public static void isTrue(boolean shouldBeTrue, String errormessage) {
		if ( ! shouldBeTrue) {
			throw new IllegalArgumentException(errormessage);
		}
	}

}
