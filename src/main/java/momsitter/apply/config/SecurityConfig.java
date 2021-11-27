package momsitter.apply.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import momsitter.apply.config.properties.JwtTokenProperty;
import momsitter.apply.filter.LoginFilter;
import momsitter.apply.interceptor.AuthenticationInterceptor;
import momsitter.apply.security.Jwt;
import momsitter.apply.security.JwtProvider;
import momsitter.apply.user.service.UserService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

	private final JwtTokenProperty jwtTokenProperty;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Jwt jwt() {
		return new Jwt(jwtTokenProperty.getSecret(), jwtTokenProperty.getExpiration());
	}

	@Bean
	public JwtProvider jwtProvider(@Lazy UserService userService) {
		return new JwtProvider(userService, jwt());
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter(@Lazy UserService userService) {
		FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<>(
			new LoginFilter(jwtProvider(userService), jwt()));
		registration.addUrlPatterns("/api/login");
		registration.setOrder(1);
		return registration;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor(jwt()))
			.addPathPatterns("/api/**")
			.excludePathPatterns("/api/login");
	}
}
