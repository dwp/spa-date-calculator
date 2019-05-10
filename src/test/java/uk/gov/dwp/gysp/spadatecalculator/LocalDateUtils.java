package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtils {

	static LocalDate createLocalDate(final int dayOfMonth, final int month, final int year) {
		return LocalDate.of(year, month, dayOfMonth);
	}

	static LocalDate createLocalDate(final String dMMMMyyyy) {
		return LocalDate.parse(dMMMMyyyy, getStandardDateFormatter());
	}

	static String format(final LocalDate date) {
		return getStandardDateFormatter().format(date);
	}

	static Date toDate(final LocalDate ld) {
		if (null == ld) {
			return null;
		}
		return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	static LocalDate toLocalDate(final Date date) {
		if (null == date) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private static DateTimeFormatter getStandardDateFormatter() {
		return DateTimeFormatter.ofPattern("d MMMM yyyy");
	}

}
