package momsitter.apply.filter;

import java.io.IOException;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import momsitter.apply.exceptions.NotFoundException;
import momsitter.apply.security.Jwt;
import momsitter.apply.security.JwtProvider;
import momsitter.apply.security.JwtUser;
import momsitter.apply.security.dto.LoginRequestDto;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter implements Filter {

	private final JwtProvider jwtProvider;
	private final Jwt jwt;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		try {
			LoginRequestDto req = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
			JwtUser user = jwtProvider.authenticate(req);
			success((HttpServletResponse)response, user);
			return;
		} catch (NotFoundException e) {
			log.error("Not found exception : {}", e.getMessage());
		} catch (FailedLoginException e) {
			log.error("Password fail exception : {}", e.getMessage());
		}
		failure((HttpServletResponse)response);
	}

	private void success(HttpServletResponse response, JwtUser user) throws IOException {
		String token = jwt.createToken(user);
		response.setHeader(Jwt.HEADER_KEY, Jwt.BEARER + token);
		response.getOutputStream().print(token);
	}

	private void failure(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());
	}
}
