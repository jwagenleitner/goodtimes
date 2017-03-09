package bdkosher.goodtimes

import java.time.*
import java.time.chrono.*
import java.time.format.*
import java.time.temporal.*
import spock.lang.Specification

class LocalTimeExtensionSpec extends Specification {

    def "toDate works decently enough as you coult expect"() {
        given:
        LocalTime time = LocalTime.of(12, 34, 56)

        when:
        Date date = time.toDate()

        then:
        date.format('hh:mm:ss.SSS') == '12:34:56.000'
    }

    def "plus seconds"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + 2

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute
        mod.second == orig.second + 2
        mod.nano == 0
    }

    def "minus seconds"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig - 2

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute
        mod.second == orig.second - 2
        mod.nano == 0
    }

    def "next second"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime next = orig.next()

        expect:
        next.second == orig.second + 1
    }

    def "previous second"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime prev = orig.previous()

        expect:
        prev.second == orig.second - 1
    }    

    def "plus Duration of seconds only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + Duration.ofSeconds(1)

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute
        mod.second == orig.second + 1
    }

    def "plus Duration of negative seconds only"() {
        given:
        LocalTime orig = LocalTime.of(12, 23, 56)
        LocalTime mod = orig + Duration.ofSeconds(-1)

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute
        mod.second == orig.second - 1
    }    

    def "plus Duration of minutes only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + Duration.ofMinutes(1)

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute + 1
        mod.second == orig.second
    }

    def "plus Duration of hours only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + Duration.ofHours(1)

        expect: 
        mod.hour == orig.hour + 1
        mod.minute == orig.minute
        mod.second == orig.second
    }

    def "plus Duration of hours, minutes, and seconds"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + Duration.ofHours(1).plusMinutes(1).plusSeconds(1)

        expect:
        mod.hour == orig.hour + 1
        mod.minute == orig.minute + 1
        mod.second == orig.second + 1
    }

    def "plus Duration of negative hours, minutes, and seconds"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig + Duration.ofHours(-1).plusMinutes(-1).plusSeconds(-1)

        expect:
        mod.hour == orig.hour - 1
        mod.minute == orig.minute - 1
        mod.second == orig.second - 1
    }    

    def "minus Duration of seconds only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig - Duration.ofSeconds(1)

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute
        mod.second == orig.second - 1
    }

    def "minus Duration of minutes only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig - Duration.ofMinutes(1)

        expect:
        mod.hour == orig.hour
        mod.minute == orig.minute - 1
        mod.second == orig.second
    }

    def "minus Duration of hours only"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig - Duration.ofHours(1)

        expect:
        mod.hour == orig.hour - 1
        mod.minute == orig.minute
        mod.second == orig.second
    }

    def "minus Duration of days, months, and hours"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig - Duration.ofHours(1).plusMinutes(1).plusSeconds(1)

        expect:
        mod.hour == orig.hour - 1
        mod.minute == orig.minute - 1
        mod.second == orig.second - 1
    }

    def "minus Duration of negative days, months, and hours"() {
        given:
        LocalTime orig = LocalTime.of(12, 34, 56)
        LocalTime mod = orig -Duration.ofHours(-1).plusMinutes(-1).plusSeconds(-1)

        expect:
        mod.hour == orig.hour + 1
        mod.minute == orig.minute + 1
        mod.second == orig.second + 1
    }

    def "getAt Calendar field"() {
        given:
        LocalTime lt = LocalTime.of(12, 34, 56)

        expect:
        lt[Calendar.HOUR] == 12
        lt[Calendar.MINUTE] == 34
        lt[Calendar.SECOND] == 56
        lt[Calendar.MILLISECOND] == 0
    }

    def "getAt unsupported Calendar field"() {
        setup:
        LocalTime lt = LocalTime.of(12, 34, 56)

        when:
        lt[Calendar.ERA]

        then:
            thrown IllegalArgumentException
    }

    def "getAt invalid Calendar field"() {
        setup:
        LocalTime lt = LocalTime.of(12, 34, 56)

        when:
        lt[Integer.MAX_VALUE]

        then:
        thrown IllegalArgumentException
    }

    def "getAt Temporal Field"() {
        given:
        LocalTime lt = LocalTime.of(12, 34, 56)
        int minuteOfDay = (12 * 60) + 34
        int secondOfDay = (12 * 60 * 60) + (34 * 60) + 56

        expect:
        lt[ChronoField.HOUR_OF_AMPM] == 0
        lt[ChronoField.HOUR_OF_DAY] == 12
        lt[ChronoField.MINUTE_OF_DAY] == minuteOfDay
        lt[ChronoField.MINUTE_OF_HOUR] == 34
        lt[ChronoField.SECOND_OF_DAY] == secondOfDay
        lt[ChronoField.SECOND_OF_MINUTE] == 56
        lt[ChronoField.MILLI_OF_DAY] == secondOfDay * 1e3
        lt[ChronoField.MILLI_OF_SECOND] == 0
        lt[ChronoField.MICRO_OF_DAY] == secondOfDay * 1e6
        lt[ChronoField.MICRO_OF_SECOND] == 0
        lt[ChronoField.NANO_OF_DAY] == secondOfDay * 1e9
        lt[ChronoField.NANO_OF_SECOND] == 0
    }

    def "duration between now and an-hour-from-now is one hour"() {
        given:
        LocalTime now = LocalTime.now()
        LocalTime hourFromNow = now.plusHours(1)
        Duration duration = now - hourFromNow

        expect:
        duration.seconds == 60 * 60
    }

    def "format by pattern with US Locale"() {
        given:
        LocalTime t = LocalTime.of(12, 34, 56)
        String pattern = 'hhmmssSSS'

        expect:
        t.format(pattern, Locale.US) == '123456000'
    }

    def "format by FormatStyle"() {
        given:
        LocalTime t = LocalTime.of(12, 34, 56)

        expect:
        t.format(FormatStyle.SHORT) == t.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
    }

    def "getTimeString is just an alias for format FormatStyle.SHORT "() {
        given:
        LocalTime t = LocalTime.of(12, 34, 56)

        expect:
        t.timeString == t.format(FormatStyle.SHORT)
    }

    def "duration between an-hour-from-now and now is negative one hour"() {
        given:
        LocalTime now = LocalTime.now()
        LocalTime hourFromNow = now.plusHours(1)
        Duration duration = hourFromNow - now

        expect:
        duration.seconds == -1 * 60 * 60
    }

    def "downto() cannot be called with date after this date"() {
        setup:
        LocalTime now = LocalTime.now()
        LocalTime later = now + 1

        when:
        now.downto(later) { d -> 
            throw new Exception('This closure body shoult never get executed.') 
        }

        then:
        thrown GroovyRuntimeException
    }

    def "downto() is called once when the two dates are the same"() {
        setup:
        LocalTime now = LocalTime.now()
        boolean closureCalledOnce = false

        when:
        now.downto(now) { d -> 
            closureCalledOnce = true
        }

        then:
        closureCalledOnce
    }

    def "downto() can be passed no-arg closure"() {
        setup:
        LocalTime now = LocalTime.now()
        LocalTime oneMinuteAgo = now - 60
        int count = 0

        when:
        now.downto(oneMinuteAgo) {
            count++
        }

        then:
        count == 61
    }

    def "upto() cannot be called with date before this date"() {
        setup:
        LocalTime now = LocalTime.now()
        LocalTime oneSecondAgo = now - 1

        when:
        now.upto(oneSecondAgo) { d -> 
            throw new Exception('This closure body shoult never get executed.') 
        }

        then:
        thrown GroovyRuntimeException
    }

    def "upto() is called once when the two dates are the same"() {
        setup:
        LocalTime now = LocalTime.now()
        boolean closureCalledOnce = false

        when:
        now.upto(now) { d -> 
            closureCalledOnce = true
        }

        then:
        closureCalledOnce
    }

    def "upto() can be passed no-arg closure"() {
        setup:
        LocalTime now = LocalTime.now()
        LocalTime oneMinuteFromNow = now + 60
        int count = 0

        when:
        now.upto(oneMinuteFromNow) {
            count++
        }

        then:
        count == 61
    }    
}