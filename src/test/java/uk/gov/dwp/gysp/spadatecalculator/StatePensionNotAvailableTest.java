package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;

@ApplicationTestConfiguration
public class StatePensionNotAvailableTest implements StatePensionDateTestData {

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
    final String errorDescription = String.format(
      "State Pension Age should not be available for %s born on %s",
      gender, format(dob)
    );
    assertNull(this.service.findSpDate(gender, toDate(dob)).statePensionDate(), errorDescription);
  }

}
