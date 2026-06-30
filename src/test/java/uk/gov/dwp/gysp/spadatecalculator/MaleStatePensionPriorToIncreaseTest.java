package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

@ApplicationTestConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MaleStatePensionPriorToIncreaseTest implements StatePensionDateTestData {

  @Autowired
  private SpDateCalculator service;

  Stream<Arguments> data() {
    return Stream.of(new StatePensionStartDateRule(
                   Gender.MALE,
                   firstDob("6 April 1951"),
                   lastDob("5 December 1953"),
                   65
                 ))
                 .map(Arguments::of);
  }

  @ParameterizedTest
  @MethodSource("data")
  void supportedMaleDateRange_returnsExpectedStatePensionDate_happyPath(final StatePensionStartDateRule rule) {
    assertStatePensionDateForAllApplicableGenders(this.service, rule);
  }
}
