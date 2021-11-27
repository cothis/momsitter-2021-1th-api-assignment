package momsitter.apply.user.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Parent {
	private Long no;

	private String userId;

	private String requestMessage;

	private List<Children> children;

	public void setChildren(List<Children> children) {
		this.children = children;
	}
}
