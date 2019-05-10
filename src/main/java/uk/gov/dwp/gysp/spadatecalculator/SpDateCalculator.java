package uk.gov.dwp.gysp.spadatecalculator;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@PropertySource("classpath:spatable.properties")
public class SpDateCalculator {

    private static final int AGE_65 = 65;

    private static final int AGE_66 = 66;

    private static final Date END_OF_EQUALISATION_PERIOD = new GregorianCalendar(1953, Calendar.DECEMBER, 5).getTime();

    private static final Date FIRST_SUPPORTED_DATE_FOR_MEN = new GregorianCalendar(1951, Calendar.APRIL, 6).getTime();

    private static final Date FIRST_SUPPORTED_DATE_FOR_FEMALE = new GregorianCalendar(1953, Calendar.APRIL, 6).getTime();
    
    private static final Date LAST_SUPPORTED_CALCULATION_DATE = new GregorianCalendar(1960, Calendar.APRIL, 5).getTime();

    private static final Date START_OF_66TH_BIRTHDAY_RULE = new GregorianCalendar(1954, Calendar.OCTOBER, 6).getTime();

    private static final long MONTHS_TO_ADD = 4;
    
    private static final String MATURE_CLAIM = "Mature claim";
    
    private static final String PRE_MATURE_CLAIM = "Pre-Mature claim";
    
    private static final String NSP_CLAIM = "Non state pension customer";

    private static final Logger LOGGER = LoggerFactory.getLogger(SpDateCalculator.class);

    @Value("${equalisationPeriod.dobrangeend}")
    private Date[] equalisationPeriodDobRangeEnd;

    @Value("${equalisationPeriod.dobrangestart}")
    private Date[] equalisationPeriodDobRangeStart;

    private SpaTable equalisationPeriodSpaTable;

    @Value("${equalisationPeriod.spdate}")
    private Date[] equalisationPeriodSpDates;

    @Value("${increaseInPensionAgePeriod.dobrangeend}")
    private Date[] increaseInPensionAgePeriodDobRangeEnd;

    @Value("${increaseInPensionAgePeriod.dobrangestart}")
    private Date[] increaseInPensionAgePeriodDobRangeStart;

    private SpaTable increaseInPensionAgePeriodSpaTable;

    @Value("${increaseInPensionAgePeriod.spdates}")
    private Date[] spDates;

    public Map<String,Date> findSpDate(final Gender gender, final Date dob) {
        
        Map<String,Date> spaMap= new HashMap<>();

        if (gender.equals(Gender.MALE) && withinRange(dob, FIRST_SUPPORTED_DATE_FOR_MEN, END_OF_EQUALISATION_PERIOD)) {
            Date spadate = calculateSpDateBasedOnBirthday(dob, AGE_65);
            return getSpaDatewithStatus(spadate);
        }

        if (gender.equals(Gender.FEMALE) && this.equalisationPeriodSpaTable.isInRange(dob)) {
            Date spadate = equalisationPeriodSpaTable.spDateForDateRange(dob);
            spadate = DateUtils.addHours(spadate, 6);
            return getSpaDatewithStatus(spadate);
        }

        if (this.increaseInPensionAgePeriodSpaTable.isInRange(dob)) {
            Date spadate = increaseInPensionAgePeriodSpaTable.spDateForDateRange(dob);
            spadate = DateUtils.addHours(spadate, 6);
            return getSpaDatewithStatus(spadate);
        }

        if (withinRange(dob, START_OF_66TH_BIRTHDAY_RULE, LAST_SUPPORTED_CALCULATION_DATE)) {
            Date spadate = calculateSpDateBasedOnBirthday(dob, AGE_66);
            return getSpaDatewithStatus(spadate);
        }

        if (beforeFirstSupportedDate(gender, dob)) {
            spaMap.put(NSP_CLAIM, null);
            return spaMap;
        }

        if (dob.after(LAST_SUPPORTED_CALCULATION_DATE)) {
            spaMap.put(PRE_MATURE_CLAIM, null);
            return spaMap;
        }

        LOGGER.error("Spa date not found for gender {} and dob {}",gender.getGender(),dob);
        return null;

    }

    @PostConstruct
    void initReferenceData() {
        this.increaseInPensionAgePeriodSpaTable = new SpaTable(this.increaseInPensionAgePeriodDobRangeStart,
                this.increaseInPensionAgePeriodDobRangeEnd, this.spDates);
        this.equalisationPeriodSpaTable = new SpaTable(this.equalisationPeriodDobRangeStart,
                this.equalisationPeriodDobRangeEnd, this.equalisationPeriodSpDates);
    }

    public Boolean isClaimMature(final Date spaDate, final LocalDate currentDate) {
        final LocalDate statePensionDate = spaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final LocalDate eligibleDate = currentDate.plusMonths(MONTHS_TO_ADD);
        return (statePensionDate.isBefore(currentDate) || statePensionDate.equals(currentDate) || eligibleDate.equals(statePensionDate) || eligibleDate.isAfter(statePensionDate));
    }

    public Map<String, Date> getSpaDatewithStatus(final Date spaDate) {
        Map<String, Date> spaMap = new HashMap<>();
        if (isClaimMature(spaDate, LocalDate.now())) {
            spaMap.put(MATURE_CLAIM, spaDate);
            return spaMap;
        } else {
            spaMap.put(PRE_MATURE_CLAIM, spaDate);
            return spaMap;
        }
    }

    private static Date calculateSpDateBasedOnBirthday(final Date dob, final int years) {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(dob);

        if (isLeapYear(calendar) && (calendar.get(Calendar.DAY_OF_MONTH) == 29)
                && (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    private static boolean isLeapYear(final Calendar toCheck) {
        return new GregorianCalendar().isLeapYear(toCheck.get(Calendar.YEAR));
    }

    private static boolean withinRange(final Date date, final Date startDate, final Date endDate) {
        return !(date.before(startDate) || dateAfterOrNotOnSameDayCheck(date,endDate));
    }

    private static boolean dateAfterOrNotOnSameDayCheck(final Date date, final Date endDate) {

        if (DateUtils.isSameDay(date,endDate)) {
            return false;
        }
        return date.after(endDate);
    }

    private boolean beforeFirstSupportedDate(final Gender gender, final Date dob) {
        return (gender.equals(Gender.MALE) && (dob.before(FIRST_SUPPORTED_DATE_FOR_MEN))) || (gender.equals(Gender.FEMALE) && (dob.before(FIRST_SUPPORTED_DATE_FOR_FEMALE)));
    }
}
