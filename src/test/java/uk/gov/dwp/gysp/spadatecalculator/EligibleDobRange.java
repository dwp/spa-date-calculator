package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;

public class EligibleDobRange {

	private final LocalDate first;
	private final LocalDate last;

	EligibleDobRange(final LocalDate first, final LocalDate last) {
		this.first = first;
		this.last = last;
		if (this.first.isAfter(this.last)) {
			throw new IllegalStateException();
		}
	}

	LocalDate getFirst() {
		return this.first;
	}

	boolean in(final LocalDate toCheck) {
		if (null == toCheck) {
			return false;
		}
		if (toCheck.isBefore(this.first)) {
			return false;
		}
		if (toCheck.isAfter(this.last)) {
			return false;
		}
		return true;
	}
}
