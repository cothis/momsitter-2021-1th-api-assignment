package momsitter.apply.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Jwt {
	public static final String BEARER = "Bearer ";
	public static final String HEADER_KEY = "Authorization";
	private final String secret;
	private final int expiration;

	public String createToken(JwtUser user) {
		return Jwts.builder()
			.setSubject(user.getEmail())
			.setExpiration(new Date((System.currentTimeMillis() + expiration)))
			.claim("id", user.getId())
			.signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
			.compact();
	}

	public JwtUser convertToJwtUser(HttpServletRequest request) {
		return getUser(getToken(request));
	}

	public JwtUser getUser(String token) {
		if (token == null) {
			return null;
		}

		Claims claims = Jwts.parserBuilder()
			.setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
			.build()
			.parseClaimsJws(token)
			.getBody();
		return new JwtUser(claims.get("id", String.class), claims.getSubject());
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_KEY);
		if (token == null) {
			return null;
		}

		try {
			token = URLDecoder.decode(token, StandardCharsets.UTF_8.name());
			String[] schemeAndCredential = token.split(" ");
			if (schemeAndCredential.length != 2) {
				return null;
			}

			String scheme = schemeAndCredential[0];
			String credential = schemeAndCredential[1];
			return BEARER.trim().equals(scheme) ? credential : null;
		} catch (UnsupportedEncodingException e) {
			log.error("unsupported charsets exception : " + e.getMessage(), e);
			return null;
		}
	}
}
