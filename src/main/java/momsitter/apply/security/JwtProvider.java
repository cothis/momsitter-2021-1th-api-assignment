package momsitter.apply.security;

import javax.security.auth.login.FailedLoginException;

import lombok.RequiredArgsConstructor;
import momsitter.apply.security.dto.LoginRequestDto;
import momsitter.apply.user.domain.User;
import momsitter.apply.user.service.UserService;

@RequiredArgsConstructor
public class JwtProvider {
	private final UserService userService;
	private final Jwt jwt;

	public JwtUser authenticate(LoginRequestDto req) throws FailedLoginException {
		User user = userService.login(req.getId(), req.getPassword());
		return new JwtUser(user.getId(), user.getEmail());
	}
}
