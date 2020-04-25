package guru.springframework.msscbeerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Custom component to map fields between OffsetDateTime
 * and java.sql.Timestamp. Can instruct MapStruct to use
 * this mapper
 */
@Component
public class DateMapper {

    /**
     * Map a java.sql.Timestamp to an OffsetDateTime
     * @param ts the java.sql.Timestamp to convert
     * @return OffsetDateTime
     */
    public OffsetDateTime asOffsetDateTime(Timestamp ts) {

        if(ts == null) {
            return null;
        }

        LocalDateTime lts = ts.toLocalDateTime();
        return OffsetDateTime.of(
                lts.getYear(),
                lts.getMonthValue(),
                lts.getDayOfMonth(),
                lts.getHour(),
                lts.getMinute(),
                lts.getSecond(),
                lts.getNano(),
                ZoneOffset.UTC);
    }

    /**
     * Map an OffsetDateTime to java.sql.Timestamp
     * @param odt the OffsetDateTime to convert
     * @return java.sql.Timestamp
     */
    public Timestamp asTimestamp(OffsetDateTime odt) {
        if(odt == null) {
            return null;
        }
        return Timestamp.valueOf(odt.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }
}
