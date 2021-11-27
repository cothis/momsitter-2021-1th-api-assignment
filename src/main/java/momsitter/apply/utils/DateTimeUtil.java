package momsitter.apply.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTimeUtil {
	public static LocalDateTime dateTimeOf(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toLocalDateTime();
	}

	public static Timestamp timestampOf(LocalDateTime dateTime) {
		return dateTime == null ? null : Timestamp.valueOf(dateTime);
	}
}
