/**
 * 
 */
package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.data.mongodb.port = 0")
public class StatePensionDayLightSavingsTest {

    @Autowired
    private SpDateCalculator service;

    @Test
    public void spaDateIsCorrectInUTCWhenDobInBSTAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 1);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-10-01", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobInGMTAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 10);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-10-10", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobOnGMTStartBorderAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 5);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-10-05", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobOnGMTEndBorderAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 28);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-10-28", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobInGMTInMarchAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 4);
        now.set(Calendar.MONTH, 2);
        Date dob = now.getTime();

        now.set(Calendar.YEAR, 2018);

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-03-04", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobOnGMTInMarchStartBorderAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 26);
        now.set(Calendar.MONTH, 2);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-03-26", getFormattedDate(actual.statePensionDate()));
    }

    @Test
    public void spaDateIsCorrectInUTCWhenDobOnGMTInMarchEndBorderAndSPAInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 19);
        now.set(Calendar.MONTH, 3);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.MALE, dob);
        assertEquals("2018-04-19", getFormattedDate(actual.statePensionDate()));
    }


    @Test
    public void femaleSpaDateIsCorrectForEqualisationInBST() {

        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.MONTH, 10);
        Date dob = now.getTime();

        StatePensionResult actual = service.findSpDate(Gender.FEMALE, dob);
        assertEquals("2018-07-06", getFormattedDate(actual.statePensionDate()));
    }

    private Calendar getCalendar() {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        now.set(Calendar.YEAR, 1953);
        now.set(Calendar.MONTH, 9);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return now;
    }


    private String getFormattedDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }
}
