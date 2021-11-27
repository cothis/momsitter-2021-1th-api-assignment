package momsitter.apply.user.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class User {
	private Long no;

	private String name;

	private LocalDateTime birthday;

	private String gender;

	private String id;

	private String password;

	private String email;
}
