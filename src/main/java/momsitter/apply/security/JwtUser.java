package momsitter.apply.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class JwtUser {
	private final String id;
	private final String email;
}
