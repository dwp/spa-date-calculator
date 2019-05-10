package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class WomansStatePensionDatePensionAct2011Test extends StatePensionDatePensionActTestBase {

	@Parameters
	public static Collection<StatePensionStartDateRule> data() {
		return Arrays.asList(new StatePensionStartDateRule[] {
				new EqualisationStatePensionStartDateRule(firstDob("6 April 1953"), lastDob("5 May 1953"),
						pensionDate("6 July 2016")),
				new EqualisationStatePensionStartDateRule(firstDob("6 May 1953"), lastDob("5 June 1953"),
						pensionDate("6 November 2016")),
				new EqualisationStatePensionStartDateRule(firstDob("6 June 1953"), lastDob("5 July 1953"),
						pensionDate("6 March 2017")),
				new EqualisationStatePensionStartDateRule(firstDob("6 July 1953"), lastDob("5 August 1953"),
						pensionDate("6 July 2017")),
				new EqualisationStatePensionStartDateRule(firstDob("6 August 1953"), lastDob("5 September 1953"),
						pensionDate("6 November 2017")),
				new EqualisationStatePensionStartDateRule(firstDob("6 September 1953"), lastDob("5 October 1953"),
						pensionDate("6 March 2018")),
				new EqualisationStatePensionStartDateRule(firstDob("6 October 1953"), lastDob("5 November 1953"),
						pensionDate("6 July 2018")),
				new EqualisationStatePensionStartDateRule(firstDob("6 November 1953"), lastDob("5 December 1953"),
						pensionDate("6 November 2018")) });
	}

	public WomansStatePensionDatePensionAct2011Test(final StatePensionStartDateRule rule) {
		super(rule);
	}
}