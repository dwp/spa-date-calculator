package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public interface LocalDateUtils {

  default LocalDate createLocalDate(final int dayOfMonth, final int month, final int year) {
    return LocalDate.of(year, month, dayOfMonth);
  }

  default LocalDate createLocalDate(final String dMMMMyyyy) {
    return LocalDate.parse(dMMMMyyyy, getStandardDateFormatter());
  }

  default String format(final LocalDate date) {
    return getStandardDateFormatter().format(date);
  }

  default Date toDate(final LocalDate ld) {
    if (null == ld) {
      return null;
    }
    return Date.from(ld.atStartOfDay(ZoneId.systemDefault())
                       .toInstant());
  }

  default LocalDate toLocalDate(final Date date) {
    if (null == date) {
      return null;
    }
    return date.toInstant()
               .atZone(ZoneId.systemDefault())
               .toLocalDate();
  }

  default DateTimeFormatter getStandardDateFormatter() {
    return DateTimeFormatter.ofPattern("d MMMM yyyy");
  }
}
