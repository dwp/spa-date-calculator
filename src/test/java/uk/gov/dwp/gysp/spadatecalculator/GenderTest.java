package uk.gov.dwp.gysp.spadatecalculator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import uk.gov.dwp.gysp.spadatecalculator.Gender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GenderTest {

	private static String generateRandomInput() {
		String random = RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10));
		while (random.equals("Female") || random.equals("Male")) {
			random = RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10));
		}
		return random;
	}

	@Test
	public void femaleValueCanBeConverted() {
		// Given
		final String input = "Female";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertEquals("Result not as expected.", Gender.FEMALE, result);
	}

	@Test
	public void femaleValueLowercaseCannotBeConverted() {
		// Given
		final String input = "female";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

	@Test
	public void femaleValueUppercaseCannotBeConverted() {
		// Given
		final String input = "FEMALE";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

	@Test
	public void maleValueCanBeConverted() {
		// Given
		final String input = "Male";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertEquals("Result not as expected.", Gender.MALE, result);
	}

	@Test
	public void maleValueLowercaseCannotBeConverted() {
		// Given
		final String input = "male";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

	@Test
	public void maleValueUppercaseCannotBeConverted() {
		// Given
		final String input = "MALE";

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

	@Test
	public void nullValueCannotBeConverted() {
		// Given
		final String input = null;

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

	@Test
	public void unknownValueCannotBeConverted() {
		// Given
		final String input = generateRandomInput();

		// When
		final Gender result = Gender.genderOf(input);

		// Then
		assertNull("Result should be null as value cannot be converted to java gender value.", result);
	}

}
