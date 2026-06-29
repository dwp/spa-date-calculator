package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface StatePensionDateTestData extends LocalDateUtils {

  default LocalDate firstDob(final String dMMMMyyyy) {
    return createLocalDate(dMMMMyyyy);
  }

  default LocalDate lastDob(final String dMMMMyyyy) {
    return createLocalDate(dMMMMyyyy);
  }

  default LocalDate pensionDate(final String dMMMMyyyy) {
    return createLocalDate(dMMMMyyyy);
  }

  default void assertStatePensionAsExpectedFor(
    final SpDateCalculator service, final Gender gender, final LocalDate dob,
    final LocalDate expectedDate
  ) {
    final String description = String.format(
      "Unexpected State Pension Date for %s with DoB of %s", gender,
      format(dob)
    );
    assertEquals(expectedDate, calculateStatePensionDate(service, gender, dob), description);
  }

  default void assertStatePensionDateForAllApplicableGenders(
    final SpDateCalculator service,
    final StatePensionStartDateRule ruleToCheck
  ) {
    final EligibleDobRange dateRange = ruleToCheck.getEligibleDobRange();
    LocalDate dob = dateRange.getFirst();
    do {
      for (final Gender gender : ruleToCheck.getApplicableGenders()) {
        assertStatePensionAsExpectedFor(service, gender, dob, ruleToCheck.getPensionStartDate(dob));
      }
      dob = dob.plusDays(1);
    } while (dateRange.in(dob));
  }

  default LocalDate calculateStatePensionDate(
    final SpDateCalculator service,
    final Gender gender,
    final LocalDate dob
  ) {
    final StatePensionResult result = service.findSpDate(gender, toDate(dob));
    return toLocalDate(result.statePensionDate());
  }
}
