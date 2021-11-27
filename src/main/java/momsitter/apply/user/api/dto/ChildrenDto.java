package momsitter.apply.user.api.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import momsitter.apply.user.domain.Children;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChildrenDto {
	@JsonIgnore
	private Long no;

	@JsonIgnore
	private Long parentNo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime birthday;

	private String gender;

	public ChildrenDto(Children child) {
		BeanUtils.copyProperties(child, this);
	}
}
