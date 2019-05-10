package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;

public class EqualisationStatePensionStartDateRule extends StatePensionStartDateRule {

	EqualisationStatePensionStartDateRule(final LocalDate firstEligibleDob, final LocalDate lastEligibleDob,
			final LocalDate startDate) {
		super(Gender.FEMALE, firstEligibleDob, lastEligibleDob, startDate);
	}
}
