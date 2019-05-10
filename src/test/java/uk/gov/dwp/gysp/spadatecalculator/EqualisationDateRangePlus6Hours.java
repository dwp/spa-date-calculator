package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.toLocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class}, properties = "spring.data.mongodb.port = 0")
public class EqualisationDateRangePlus6Hours {

    @Autowired
    private SpDateCalculator service;

    @Test
    public void testDateBoundaryEndPlus6HoursForFemale() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2018-11-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.FEMALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForFemaleTest2() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-11-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2018-07-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.FEMALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForFemaleTest3() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2018-11-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.FEMALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryStartPlus6HoursForFemale() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-06-06 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2017-03-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.FEMALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForMaleSpaDateOf65() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2018-12-05");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForMaleLastSupportedCalc() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1960-04-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2026-04-05");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryStartPlus6HoursForMaleLastSupportedCalc() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-06 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2020-10-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriod() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-01-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2019-03-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriodTest2() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2020-09-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriodTest3() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-01-05 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2019-03-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryStartPlus6HoursForIncreasePensionAgePeriodTest1() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-02-06 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2019-07-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryStartPlus6HoursForIncreasePensionAgePeriodTest2() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-06 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2019-03-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.MALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }

    @Test
    public void testDateBoundaryStartOf66birthDayruleforFemale() throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-06 06:00:00");
        LocalDate expectedDate = LocalDate.parse("2020-10-06");
        final Map<String,Date> spaMap = this.service.findSpDate(Gender.FEMALE,d);
        assertEquals("Spa Date not as expected",expectedDate,toLocalDate(spaMap.values().iterator().next()));

    }
}
