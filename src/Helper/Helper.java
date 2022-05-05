package Helper;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.TimeZone;

public class Helper {


    public static Timestamp toUTC(Timestamp enteredTime) {
        ZoneId LocalZone = ZoneId.systemDefault();
        LocalDate LocalDate = enteredTime.toLocalDateTime().toLocalDate();
        LocalTime LocalTime = enteredTime.toLocalDateTime().toLocalTime();
        ZoneId UTCZoneId = ZoneId.of("UTC");
        ZonedDateTime UTCZDT = ZonedDateTime.of(LocalDate, LocalTime, LocalZone);

        Instant UTCtoGMTInstant = UTCZDT.toInstant();
        ZonedDateTime UTCtoUTCZDT = UTCZDT.withZoneSameInstant(UTCZoneId);
        ZonedDateTime gmtToLocalZDT = UTCtoGMTInstant.atZone(UTCZoneId);

        Timestamp returnedTimestamp = Timestamp.valueOf(UTCtoUTCZDT.toLocalDateTime());
        System.out.println(returnedTimestamp);
        return returnedTimestamp;

    }

    public static Timestamp toEasternTime(Timestamp TimeEntered) {
        ZoneId LocalZone = ZoneId.systemDefault();
        LocalDate LocalDate = TimeEntered.toLocalDateTime().toLocalDate();
        LocalTime LocalTime = TimeEntered.toLocalDateTime().toLocalTime();
        ZoneId ESTZoneId = ZoneId.of("US/Eastern");
        ZonedDateTime UTCZDT = ZonedDateTime.of(LocalDate, LocalTime, LocalZone);

        Instant UTCtoGMTInstant = UTCZDT.toInstant();
        ZonedDateTime UTCtoESTZDT = UTCZDT.withZoneSameInstant(ESTZoneId);
        ZonedDateTime gmtToLocalZDT = UTCtoGMTInstant.atZone(ESTZoneId);

        Timestamp returnedTimestamp = Timestamp.valueOf(UTCtoESTZDT.toLocalDateTime());
        System.out.println(returnedTimestamp);
        return returnedTimestamp;

    }
}