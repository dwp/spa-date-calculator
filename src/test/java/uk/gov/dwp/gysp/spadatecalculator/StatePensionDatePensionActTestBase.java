package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.*;

@ApplicationTestConfiguration
public class StatePensionDatePensionActTestBase {

	@ClassRule
	public static final SpringClassRule SCR = new SpringClassRule();

	static LocalDate firstDob(final String dMMMMyyyy) {
		return createLocalDate(dMMMMyyyy);
	}

	static LocalDate lastDob(final String dMMMMyyyy) {
		return createLocalDate(dMMMMyyyy);
	}

	static LocalDate pensionDate(final String dMMMMyyyy) {
		return createLocalDate(dMMMMyyyy);
	}

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	private final StatePensionStartDateRule ruleToCheck;

	@Autowired
	private SpDateCalculator service;

	public StatePensionDatePensionActTestBase(final StatePensionStartDateRule rule) {
		this.ruleToCheck = rule;
	}


	@Test
	public void statePensionDateAsExpected() {
		final EligibleDobRange dateRange = this.ruleToCheck.getEligibleDobRange();
		final LocalDate first = dateRange.getFirst();

		LocalDate dob = first;
		do {
			for (Gender gender : this.ruleToCheck.getApplicableGenders()) {
				assertStatePensionAsExpectedFor(gender, dob, this.ruleToCheck.getPensionStartDate(dob));
			}
			dob = dob.plusDays(1);
		} while (dateRange.in(dob));
	}

	private void assertStatePensionAsExpectedFor(final Gender gender, final LocalDate dob,
			final LocalDate expectedDate) {
		final String description = String.format("Unexpected State Pension Date for %s with DoB of %s", gender,
				format(dob));
		assertEquals(description, expectedDate, calculateStatePensionDate(gender, dob));
	}

	private LocalDate calculateStatePensionDate(final Gender gender, final LocalDate dob) {
	    final Map<String,Date>spaMap = this.service.findSpDate(gender, toDate(dob));
	     return toLocalDate(spaMap.values().iterator().next());
	}
}