package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationTestConfiguration
public class FemaleStatePensionDatePensionAct2011Test implements StatePensionDateTestData {

  @Autowired
  private SpDateCalculator service;

  @ParameterizedTest
  @CsvSource({
    "6 April 1953,5 May 1953,6 July 2016",
    "6 May 1953,5 June 1953,6 November 2016",
    "6 June 1953,5 July 1953,6 March 2017",
    "6 July 1953,5 August 1953,6 July 2017",
    "6 August 1953,5 September 1953,6 November 2017",
    "6 September 1953,5 October 1953,6 March 2018",
    "6 October 1953,5 November 1953,6 July 2018",
    "6 November 1953,5 December 1953,6 November 2018"
  })
  void supportedFemaleDateRange_returnsExpectedStatePensionDate_happyPath(
    final String firstDob,
    final String lastDob,
    final String pensionDate
  ) {

    EqualisationStatePensionStartDateRule input = new EqualisationStatePensionStartDateRule(
      firstDob(firstDob),
      lastDob(lastDob),
      pensionDate(pensionDate)
    );

    assertStatePensionDateForAllApplicableGenders(this.service, input);
  }
}
