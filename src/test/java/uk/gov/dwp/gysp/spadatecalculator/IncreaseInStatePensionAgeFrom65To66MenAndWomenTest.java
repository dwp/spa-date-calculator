package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IncreaseInStatePensionAgeFrom65To66MenAndWomenTest extends StatePensionDatePensionActTestBase {

	@Parameters
	public static Collection<StatePensionStartDateRule> data() {
		return Arrays.asList(new StatePensionStartDateRule[] {
				new StatePensionStartDateRule(firstDob("6 December 1953"), lastDob("5 January 1954"),
						pensionDate("6 March 2019")),
				new StatePensionStartDateRule(firstDob("6 January 1954"), lastDob("5 February 1954"),
						pensionDate("6 May 2019")),
				new StatePensionStartDateRule(firstDob("6 February 1954"), lastDob("5 March 1954"),
						pensionDate("6 July 2019")),
				new StatePensionStartDateRule(firstDob("6 March 1954"), lastDob("5 April 1954"),
						pensionDate("6 September 2019")),
				new StatePensionStartDateRule(firstDob("6 April 1954"), lastDob("5 May 1954"),
						pensionDate("6 November 2019")),
				new StatePensionStartDateRule(firstDob("6 May 1954"), lastDob("5 June 1954"),
						pensionDate("6 January 2020")),
				new StatePensionStartDateRule(firstDob("6 June 1954"), lastDob("5 July 1954"),
						pensionDate("6 March 2020")),
				new StatePensionStartDateRule(firstDob("6 July 1954"), lastDob("5 August 1954"),
						pensionDate("6 May 2020")),
				new StatePensionStartDateRule(firstDob("6 August 1954"), lastDob("5 September 1954"),
						pensionDate("6 July 2020")),
				new StatePensionStartDateRule(firstDob("6 September 1954"), lastDob("5 October 1954"),
						pensionDate("6 September 2020")),
				new StatePensionStartDateRule(firstDob("6 October 1954"), lastDob("5 April 1960"), pensionAge(66)) });
	}

	private static Integer pensionAge(final int ageOfPension) {
		return Integer.valueOf(ageOfPension);
	}

	public IncreaseInStatePensionAgeFrom65To66MenAndWomenTest(final StatePensionStartDateRule rule) {
		super(rule);
	}
}
