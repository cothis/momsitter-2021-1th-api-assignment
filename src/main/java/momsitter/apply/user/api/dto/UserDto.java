package momsitter.apply.user.api.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import momsitter.apply.user.domain.Parent;
import momsitter.apply.user.domain.Sitter;
import momsitter.apply.user.domain.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	private Long no;

	private String name;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime birthday;

	private String gender;

	private String id;

	private String password;

	private String email;

	// Association
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private SitterDto sitter;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ParentDto parent;

	public UserDto(User user, Optional<Sitter> sitter, Optional<Parent> parent) {
		BeanUtils.copyProperties(user, this);
		sitter.ifPresent(s -> this.sitter = new SitterDto(s));
		parent.ifPresent(p -> this.parent = new ParentDto(p));
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setPassword(String password) {
		this.password = password;
	}
}
