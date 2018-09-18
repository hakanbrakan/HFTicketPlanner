package se.frihak.ticketplanner.validators;

public class Asserter {

	public static void isNotNull(Object notNullValue, String errormessage) {
		if (notNullValue == null) {
			throw new IllegalArgumentException(errormessage);
		}
	}

	public static void isNotEmpty(String notEmptyString, String errormessage) {
		isNotNull(notEmptyString, errormessage);
		if (notEmptyString.length() < 1) {
			throw new IllegalArgumentException(errormessage);
		}
	}
}
