package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {Application.class}, properties = "spring.data.mongodb.port = 0")
public class EqualisationDateRangePlus6Hours implements StatePensionDateTestData {

  @Autowired
  private SpDateCalculator service;

  @Test
  public void testDateBoundaryEndPlus6HoursForFemale() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2018-11-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.FEMALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForFemaleTest2() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-11-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2018-07-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.FEMALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForFemaleTest3() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2018-11-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.FEMALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryStartPlus6HoursForFemale() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-06-06 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2017-03-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.FEMALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForMaleSpaDateOf65() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2018-12-05");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForMaleLastSupportedCalc() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1960-04-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2026-04-05");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryStartPlus6HoursForMaleLastSupportedCalc() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-06 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2020-10-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriod() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-01-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2019-03-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriodTest2() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2020-09-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryEndPlus6HoursForIncreasePensionAgePeriodTest3() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-01-05 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2019-03-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryStartPlus6HoursForIncreasePensionAgePeriodTest1() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-02-06 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2019-07-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryStartPlus6HoursForIncreasePensionAgePeriodTest2() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1953-12-06 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2019-03-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.MALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }

  @Test
  public void testDateBoundaryStartOf66birthDayruleforFemale() throws Exception {
    Date d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1954-10-06 06:00:00");
    LocalDate expectedDate = LocalDate.parse("2020-10-06");
    final StatePensionResult actual = this.service.findSpDate(Gender.FEMALE, d);
    assertEquals(expectedDate, toLocalDate(actual.statePensionDate()));

  }
}
