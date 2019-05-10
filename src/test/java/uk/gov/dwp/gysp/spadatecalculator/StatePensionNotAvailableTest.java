package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.createLocalDate;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.format;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.toDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTestConfiguration
public class StatePensionNotAvailableTest {

	@Autowired
	private SpDateCalculator service;

	@Test
	public void femaleDateNotAvailableOnOrAfter06041960() {
		// Given
		final Gender gender = Gender.FEMALE;
		final LocalDate minInclusiveDate = createLocalDate(6, 4, 1960);
		final LocalDate maxExclusiveDate = LocalDate.now();
		final LocalDate randomDob = new RandomLocalDate(minInclusiveDate, maxExclusiveDate).nextDate();

		// When/Then
		assertStatePensionDateNotAvailableFor(gender, randomDob);
	}

	@Test
	public void femaleDateNotAvailableOnOrBefore05041953() {
		// Given
		final Gender gender = Gender.FEMALE;
		final LocalDate minInclusiveDate = createLocalDate(1, 1, 1940);
		final LocalDate maxExclusiveDate = createLocalDate(6, 4, 1953);
		final LocalDate randomDob = new RandomLocalDate(minInclusiveDate, maxExclusiveDate).nextDate();

		// When/Then
		assertStatePensionDateNotAvailableFor(gender, randomDob);
	}

	@Test
	public void maleDateNotAvailableOnOrAfter06041960() {
		// Given
		final Gender gender = Gender.MALE;
		final LocalDate minInclusiveDate = createLocalDate(6, 4, 1960);
		final LocalDate maxExclusiveDate = LocalDate.now();
		final LocalDate randomDob = new RandomLocalDate(minInclusiveDate, maxExclusiveDate).nextDate();

		// When/Then
		assertStatePensionDateNotAvailableFor(gender, randomDob);
	}

	@Test
	public void maleDateNotAvailableOnOrBefore05041951() {
		// Given
		final Gender gender = Gender.MALE;

		final LocalDate minInclusiveDate = createLocalDate(1, 1, 1940);
		final LocalDate maxExclusiveDate = createLocalDate(6, 4, 1951);
		final LocalDate randomDob = new RandomLocalDate(minInclusiveDate, maxExclusiveDate).nextDate();

		// When/Then
		assertStatePensionDateNotAvailableFor(gender, randomDob);
	}

    private void assertStatePensionDateNotAvailableFor(final Gender gender, final LocalDate dob) {
        // When

        final Map<String, Date> spaMap = this.service.findSpDate(gender, toDate(dob));
        final Date statePensionDate = spaMap.values().iterator().next();

        // Then
        final String errorDescription = String.format("State Pension Age should not be available for %s born on %s",
                gender, format(dob));
        assertNull(errorDescription, statePensionDate);
    }

}
