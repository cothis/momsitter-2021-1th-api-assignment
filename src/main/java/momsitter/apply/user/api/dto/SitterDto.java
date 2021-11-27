package momsitter.apply.user.api.dto;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import momsitter.apply.user.domain.Sitter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SitterDto {
	@JsonIgnore
	private Long no;

	@JsonIgnore
	private String userId;

	private String introMessage;

	private Integer careFrom;

	private Integer careTo;

	public SitterDto(Sitter sitter) {
		BeanUtils.copyProperties(sitter, this);
	}
}
