package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

@ApplicationTestConfiguration
public class StatePensionAgeIncreaseFrom65To66Test implements StatePensionDateTestData {

  @Autowired
  private SpDateCalculator service;

  Stream<Arguments> data() {
    return Stream.of(
                   new StatePensionStartDateRule(
                     firstDob("6 December 1953"),
                     lastDob("5 January 1954"),
                     pensionDate("6 March 2019")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 January 1954"),
                     lastDob("5 February 1954"),
                     pensionDate("6 May 2019")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 February 1954"),
                     lastDob("5 March 1954"),
                     pensionDate("6 July 2019")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 March 1954"),
                     lastDob("5 April 1954"),
                     pensionDate("6 September 2019")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 April 1954"),
                     lastDob("5 May 1954"),
                     pensionDate("6 November 2019")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 May 1954"),
                     lastDob("5 June 1954"),
                     pensionDate("6 January 2020")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 June 1954"),
                     lastDob("5 July 1954"),
                     pensionDate("6 March 2020")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 July 1954"),
                     lastDob("5 August 1954"),
                     pensionDate("6 May 2020")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 August 1954"),
                     lastDob("5 September 1954"),
                     pensionDate("6 July 2020")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 September 1954"),
                     lastDob("5 October 1954"),
                     pensionDate("6 September 2020")
                   ),
                   new StatePensionStartDateRule(
                     firstDob("6 October 1954"),
                     lastDob("5 April 1960"), pensionAge(66)
                   )
                 )
                 .map(Arguments::of);
  }

  private static Integer pensionAge(final int ageOfPension) {
    return Integer.valueOf(ageOfPension);
  }

  @ParameterizedTest
  @MethodSource("data")
  void supportedDateRange_returnsExpectedStatePensionDate_happyPath(final StatePensionStartDateRule rule) {
    assertStatePensionDateForAllApplicableGenders(this.service, rule);
  }
}
