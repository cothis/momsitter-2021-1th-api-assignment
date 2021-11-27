package momsitter.apply.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Sitter {
	private Long no;

	private String userId;

	private String introMessage;

	private Integer careFrom;

	private Integer careTo;
}
