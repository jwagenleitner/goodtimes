package bdkosher.goodtimes

import java.time.*
import groovy.transform.PackageScope

/**
 * Extension methods for java.time.Month
 */
class MonthExtension {

    /* Maps java.time.Month enum values to their equivalent Calendar field integer value. */
    @PackageScope
    static final Map<Month, Integer> monthToCalendarMonth = Month.values().collectEntries { [it, Calendar.@"$it"]}  

    /** 
     * Returns the Month that is {@code months} many months after this Month.
     */
    static Month plus(final Month self, int months) {
        int val = ((self.value + months - 1) % 12) + 1
        Month.of(val > 0 ? val : 12 + val)
    }

    /** 
     * Returns the Month that is {@code months} many months before this Month.
     */
    static Month minus(final Month self, int months) {
        plus(self, months * -1)
    }

    /**
     * Returns the Month after this Month.
     */
    static Month next(final Month self) {
        plus(self, 1)
    }

    /**
     * Returns the Month before this Month.
     */
    static Month previous(final Month self) {
        minus(self, 1)
    }

    /**
     * Returns the value of the month constant defined in the java.util.Calendar class. For example,
     * <code>assert Month.MARCH.calendarValue() == Calendar.MARCH</code>
     */
    static int calendarValue(final Month self) {
        monthToCalendarMonth[self]
    }

    /**
     * Returns a java.time.MonthDay for the given day of the month.
     */
    static MonthDay leftShift(final Month self, int dayOfMonth) {
        MonthDay.of(self, dayOfMonth)
    }

}