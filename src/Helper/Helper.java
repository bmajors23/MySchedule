package Helper;

import java.sql.Timestamp;
import java.time.*;
import java.util.TimeZone;

public class Helper {


    public static Timestamp toLocalTime(Timestamp UTC) {
        ZoneId UTCZone = ZoneId.of("UTC");
        LocalDate UTCDate = UTC.toLocalDateTime().toLocalDate();
        LocalTime UTCTime = UTC.toLocalDateTime().toLocalTime();
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime UTCZDT = ZonedDateTime.of(UTCDate, UTCTime, UTCZone);

        Instant UTCtoGMTInstant = UTCZDT.toInstant();
        ZonedDateTime UTCtoLocalZDT = UTCZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime gmtToLocalZDT = UTCtoGMTInstant.atZone(localZoneId);

        Timestamp returnedTimestamp = Timestamp.valueOf(UTCtoLocalZDT.toLocalDateTime());

        return returnedTimestamp;

    }
}
