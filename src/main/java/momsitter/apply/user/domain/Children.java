package momsitter.apply.user.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Children {
	private final Long no;

	private final Long parentNo;

	private final LocalDateTime birthday;

	private final String gender;
}
