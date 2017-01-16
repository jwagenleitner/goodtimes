package bdkosher.goodtimes

import java.time.*
import java.time.chrono.*
import java.time.temporal.*
import spock.lang.Specification

class LocalDateExtensionSpec extends Specification {

    def "plus days"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + 2

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth + 2
    }

    def "minus days"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig - 2

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth - 2
    }

    def "next day"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig++

        expect:
        orig.dayOfMonth == mod.dayOfMonth + 1
    }

    def "previous day"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig--

        expect:
        orig.dayOfMonth == mod.dayOfMonth - 1
    }    

    def "plus Period of days only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + Period.ofDays(1)

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth + 1
    }

    def "plus Period of negative days only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + Period.ofDays(-1)

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth - 1
    }    

    def "plus Period of months only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + Period.ofMonths(1)

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue + 1
        mod.dayOfMonth == orig.dayOfMonth
    }

    def "plus Period of years only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + Period.ofYears(1)

        expect:
        mod.year == orig.year + 1
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth
    }

    def "plus Period of days, months, and years"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig + Period.of(1, 1, 1)

        expect:
        mod.year == orig.year + 1
        mod.monthValue == orig.monthValue + 1
        mod.dayOfMonth == orig.dayOfMonth + 1
    }

    def "plus Period of negative days, months, and years"() {
        given:
        LocalDate orig = LocalDate.of(2017, 2, 8)
        LocalDate mod = orig + Period.of(-1, -1, -1)

        expect:
        mod.year == orig.year - 1
        mod.monthValue == orig.monthValue - 1
        mod.dayOfMonth == orig.dayOfMonth - 1
    }    

    def "minus Period of days only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 1, 8)
        LocalDate mod = orig - Period.ofDays(1)

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth - 1
    }

    def "minus Period of months only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 2, 8)
        LocalDate mod = orig - Period.ofMonths(1)

        expect:
        mod.year == orig.year
        mod.monthValue == orig.monthValue - 1
        mod.dayOfMonth == orig.dayOfMonth
    }

    def "minus Period of years only"() {
        given:
        LocalDate orig = LocalDate.of(2017, 2, 8)
        LocalDate mod = orig - Period.ofYears(1)

        expect:
        mod.year == orig.year - 1
        mod.monthValue == orig.monthValue
        mod.dayOfMonth == orig.dayOfMonth
    }

    def "minus Period of days, months, and years"() {
        given:
        LocalDate orig = LocalDate.of(2017, 2, 8)
        LocalDate mod = orig - Period.of(1, 1, 1)

        expect:
        mod.year == orig.year - 1
        mod.monthValue == orig.monthValue - 1
        mod.dayOfMonth == orig.dayOfMonth - 1
    }

    def "minus Period of negative days, months, and years"() {
        given:
        LocalDate orig = LocalDate.of(2017, 2, 8)
        LocalDate mod = orig - Period.of(-1, -1, -1)

        expect:
        mod.year == orig.year + 1
        mod.monthValue == orig.monthValue + 1
        mod.dayOfMonth == orig.dayOfMonth + 1
    }

    def "getAt Calendar field"() {
        given:
        LocalDate ld = LocalDate.of(2017, 1, 10)

        expect:
        ld[Calendar.YEAR] == 2017
        ld[Calendar.MONTH] == Calendar.JANUARY
        ld[Calendar.DAY_OF_MONTH] == 10
        ld[Calendar.DATE] == 10
        ld[Calendar.DAY_OF_YEAR] == 10
        ld[Calendar.WEEK_OF_MONTH] == 2
        ld[Calendar.WEEK_OF_YEAR] == 2
        ld[Calendar.DAY_OF_WEEK] == Calendar.TUESDAY
        ld[Calendar.DAY_OF_WEEK_IN_MONTH] == 3
        ld[Calendar.ERA] == GregorianCalendar.AD
    }

    def "getAt unsupported Calendar field"() {
        setup:
        LocalDate ld = LocalDate.of(2017, 1, 10)

        when:
        ld[Calendar.MILLISECOND]

        then:
            thrown IllegalArgumentException
    }

    def "getAt invalid Calendar field"() {
        setup:
        LocalDate ld = LocalDate.of(2017, 1, 10)

        when:
        ld[Integer.MAX_VALUE]

        then:
            thrown IllegalArgumentException
    }

    def "getAt Temporal Field"() {
        given:
        LocalDate ld = LocalDate.of(2017, 1, 10)

        expect:
        ld[ChronoField.YEAR] == 2017
        ld[ChronoField.MONTH_OF_YEAR] == Month.JANUARY.value
        ld[ChronoField.DAY_OF_MONTH] == 10
        ld[ChronoField.DAY_OF_YEAR] == 10
        ld[ChronoField.ALIGNED_WEEK_OF_MONTH] == 2
        ld[ChronoField.ALIGNED_WEEK_OF_YEAR] == 2
        ld[ChronoField.DAY_OF_WEEK] == DayOfWeek.TUESDAY.value
        ld[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH] == 3
        ld[ChronoField.ERA] == IsoEra.CE.value        
    }

    def "period between two dates"() {
        given:
        LocalDate today = LocalDate.now()
        LocalDate tomorrow = today.plusDays(1)
        Period period = today - tomorrow

        expect:
        period.days == 1        
    }

}