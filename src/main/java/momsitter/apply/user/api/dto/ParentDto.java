package momsitter.apply.user.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import momsitter.apply.user.domain.Parent;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ParentDto {
	@JsonIgnore
	private Long no;

	@JsonIgnore
	private String userId;

	private String requestMessage;

	private List<ChildrenDto> children;

	public ParentDto(Parent parent) {
		this.requestMessage = parent.getRequestMessage();
		this.children = parent.getChildren()
			.stream()
			.map(ChildrenDto::new)
			.collect(Collectors.toList());
	}
}
