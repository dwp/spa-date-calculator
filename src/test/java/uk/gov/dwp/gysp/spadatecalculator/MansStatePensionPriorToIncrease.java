package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MansStatePensionPriorToIncrease extends StatePensionDatePensionActTestBase {

	@Parameters
	public static Collection<StatePensionStartDateRule> data() {
		return Arrays.asList(new StatePensionStartDateRule[] { new StatePensionStartDateRule(Gender.MALE,
				firstDob("6 April 1951"), lastDob("5 December 1953"), pensionAge(65)) });
	}

	private static Integer pensionAge(final int ageOfPension) {
		return Integer.valueOf(ageOfPension);
	}

	public MansStatePensionPriorToIncrease(final StatePensionStartDateRule rule) {
		super(rule);
	}
}
