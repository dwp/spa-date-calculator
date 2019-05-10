package uk.gov.dwp.gysp.spadatecalculator;

import java.time.LocalDate;
import java.util.Random;

public class RandomLocalDate {

	private final int maxDay;
	private final int minDay;
	private final Random random;

	public RandomLocalDate(final LocalDate minInclusiveDate, final LocalDate maxExclusiveDate) {
		this.minDay = (int) minInclusiveDate.toEpochDay();
		this.maxDay = (int) maxExclusiveDate.toEpochDay();
		this.random = new Random(System.currentTimeMillis());
	}

	public LocalDate nextDate() {
		long randomDay = this.minDay + this.random.nextInt(this.maxDay - this.minDay);
		return LocalDate.ofEpochDay(randomDay);
	}
}
