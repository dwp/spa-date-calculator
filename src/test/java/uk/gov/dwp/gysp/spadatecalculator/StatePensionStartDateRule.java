package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;
import java.time.Month;

public class StatePensionStartDateRule {

	private final Gender[] applicableGenders;
	private final Integer eligibleAge;
	private final EligibleDobRange eligibleDobRange;
	private final LocalDate startDate;

	StatePensionStartDateRule(final Gender applicableGender, final LocalDate firstEligibleDob,
			final LocalDate lastEligibleDob, final Integer eligibleAge) {
		this(new Gender[] { applicableGender }, firstEligibleDob, lastEligibleDob, null, eligibleAge);
	}

	StatePensionStartDateRule(final Gender applicableGender, final LocalDate firstEligibleDob,
			final LocalDate lastEligibleDob, final LocalDate startDate) {
		this(new Gender[] { applicableGender }, firstEligibleDob, lastEligibleDob, startDate, null);
	}

	StatePensionStartDateRule(final LocalDate firstEligibleDob, final LocalDate lastEligibleDob,
			final Integer eligibleAge) {
		this(null, firstEligibleDob, lastEligibleDob, null, eligibleAge);
	}

	StatePensionStartDateRule(final LocalDate firstEligibleDob, final LocalDate lastEligibleDob,
			final LocalDate startDate) {
		this(null, firstEligibleDob, lastEligibleDob, startDate, null);
	}

	private StatePensionStartDateRule(final Gender[] applicableGenders, final LocalDate firstEligibleDob,
			final LocalDate lastEligibleDob, final LocalDate startDate, final Integer eligibleAge) {
		if (null == startDate && null == eligibleAge) {
			throw new IllegalStateException("Both start date and eligible age cannot be null.");
		}
		if (null == applicableGenders) {
			this.applicableGenders = Gender.values();
		} else {
			this.applicableGenders = applicableGenders;
		}
		this.eligibleDobRange = new EligibleDobRange(firstEligibleDob, lastEligibleDob);
		this.startDate = startDate;
		this.eligibleAge = eligibleAge;

	}

	Gender[] getApplicableGenders() {
		return this.applicableGenders;
	}

	EligibleDobRange getEligibleDobRange() {
		return this.eligibleDobRange;
	}

	LocalDate getPensionStartDate(final LocalDate dob) {
		if (null != this.startDate) {
			return this.startDate;
		}
		LocalDate adjustedDob = dob;
		if (dob.isLeapYear() && dob.getMonth() == Month.FEBRUARY && dob.getDayOfMonth() == 29) {
			adjustedDob = dob.plusDays(1);
		}
		return adjustedDob.plusYears(this.eligibleAge.longValue());
	}
}
