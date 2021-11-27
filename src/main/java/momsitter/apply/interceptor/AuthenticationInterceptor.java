package momsitter.apply.interceptor;

import java.io.IOException;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import momsitter.apply.security.Jwt;
import momsitter.apply.security.JwtUser;

@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
	private final Jwt jwt;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		log.info("Auth Interceptor Process");
		if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().equals("/api/users")) {
			return true;
		}

		try {
			JwtUser jwtUser = jwt.convertToJwtUser(request);
			if (jwtUser == null) {
				throw new AuthException("unauthorized");
			}
			request.setAttribute("user", jwtUser);
			return true;
		} catch (Exception e) {
			log.warn("unauthorized user");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return false;
		}
	}
}
