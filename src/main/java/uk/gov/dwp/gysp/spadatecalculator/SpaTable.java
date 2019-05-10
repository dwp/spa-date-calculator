package uk.gov.dwp.gysp.spadatecalculator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SpaTable {
    class TableEntry {
        private final Date end;
        private final Date pensionDate;
        private final Date start;

        private TableEntry(final Date start, final Date end, final Date pensionDate) {
            this.start = start;
            this.end = end;
            this.pensionDate = pensionDate;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this).toString();
        }

        Date getPensionDate() {
            return this.pensionDate;
        }

        Date getStart() {
            return this.start;
        }

        boolean isAfter(final Date dateToCheck) {
            if (DateUtils.isSameDay(this.end, dateToCheck)) {
                return false;
            }
            return dateToCheck.after(this.end);
        }

        boolean isBefore(final Date dateToCheck) {
            if (DateUtils.isSameDay(this.start, dateToCheck)) {
                return false;
            }
            return dateToCheck.before(this.start);
        }

        boolean withinRange(final Date dateToCheck) {
            if (this.isBefore(dateToCheck)) {
                return false;
            }
            return (!this.isAfter(dateToCheck));
        }
    }

    private List<TableEntry> entries = new LinkedList<>();

    public SpaTable(Date[] dobRangeStart, Date[] dobRangeEnd, Date[] spDates) {
        int listLength = spDates.length;
        Date[] start = dobRangeStart;
        Date[] end = dobRangeEnd;
        Date[] spDate = spDates;
        for (int i = 0; i < listLength; i++) {
            this.entries.add(new TableEntry(start[i], end[i], spDate[i]));
        }
    }

    public boolean isInRange(final Date dob) {
        if (this.entries.get(0).isBefore(dob)) {
            return false;
        }
        return (!this.entries.get(this.entries.size() - 1).isAfter(dob));
    }

    public Date spDateForDateRange(Date dob) {
        for (TableEntry entry : this.entries) {
            if (entry.withinRange(dob)) {
                return entry.getPensionDate();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

}
