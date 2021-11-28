package momsitter.apply.user.api.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
		message = "비밀번호는 영문 대,소문자와 숫자, 특수문자가 적어도 1개 이상 포함된 8 ~ 20자 문자열이어야 합니다.")
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
