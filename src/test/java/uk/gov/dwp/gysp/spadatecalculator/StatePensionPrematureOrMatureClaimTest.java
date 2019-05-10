/**
 * 
 */
package uk.gov.dwp.gysp.spadatecalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.createLocalDate;
import static uk.gov.dwp.gysp.spadatecalculator.LocalDateUtils.toDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTestConfiguration
public class StatePensionPrematureOrMatureClaimTest {

    @Autowired
    private SpDateCalculator service;

    @Test
    public void spaDateIsFutureDateInLeapYear() {

        final LocalDate spaDate = createLocalDate(29, 06, 2020);
        final LocalDate currentDate = createLocalDate(29, 02, 2020);

        assertIfClaimIsMatureFor(spaDate,currentDate );
    }

    @Test
    public void spaDateIsFutureDateInNonLeapYear() {

        final LocalDate spaDate = createLocalDate(29, 06, 2017);
        final LocalDate currentDate = createLocalDate(01, 03, 2017);

        assertIfClaimIsMatureFor(spaDate, currentDate);
    }

    @Test
    public void spaDateIsFutureDate() {

        final LocalDate spaDate = createLocalDate(31, 8, 2017);
        final LocalDate currentDate = createLocalDate(01, 05, 2017);

        assertIfClaimIsMatureFor(spaDate,currentDate);
    }

    @Test
    public void spaDateIsBackDated() {

        final LocalDate spaDate = createLocalDate(05, 07, 2017);
        final LocalDate currentDate = createLocalDate(06, 07, 2017);

        assertIfClaimIsMatureFor(spaDate,currentDate);
    }

    @Test
    public void spaDateIsSameAsClaimDate() {

        final LocalDate spaDate = createLocalDate(06, 07, 2017);
        final LocalDate currentDate = createLocalDate(06, 07, 2017);

        assertIfClaimIsMatureFor(spaDate,currentDate);
    }

    @Test
    public void spaDateIsGreaterThan4MonthsInLeapYear() {

        final LocalDate spaDate = createLocalDate(29, 06, 2020);
        final LocalDate currentDate = createLocalDate(28, 02, 2020);

        assertIfClaimIsPreMatureFor(spaDate, currentDate);
    }

    @Test
    public void spaDateIsGreaterThan4MonthsInNonLeapYear() {

        final LocalDate spaDate = createLocalDate(29, 06, 2017);
        final LocalDate currentDate = createLocalDate(28, 02, 2017);

        assertIfClaimIsPreMatureFor(spaDate,currentDate );
    }

    @Test
    public void spaDateIsGreaterThan4MonthsInFuture() {

        final LocalDate spaDate = createLocalDate(06, 11, 2017);
        final LocalDate currentDate = createLocalDate(05, 07, 2017);

        assertIfClaimIsPreMatureFor(spaDate,currentDate);
    }

    @Test
    public void spaDateIsGreaterThan4MonthsInFutureForPrematureTest() {

        final LocalDate spaDate = createLocalDate(31, 8, 2017);
        final LocalDate currentDate = createLocalDate(30, 04, 2017);

        assertIfClaimIsPreMatureFor(spaDate,currentDate);
    }

    private void assertIfClaimIsMatureFor(final LocalDate spaDate, final LocalDate currentDate) {

        final Date statePensionDate = toDate(spaDate);

        assertTrue(this.service.isClaimMature(statePensionDate,currentDate));

    }

    private void assertIfClaimIsPreMatureFor(final LocalDate spaDate, final LocalDate currentDate) {

        final Date statePensionDate = toDate(spaDate);

        assertFalse(this.service.isClaimMature(statePensionDate, currentDate));

    }

}
