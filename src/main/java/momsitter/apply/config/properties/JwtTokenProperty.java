package momsitter.apply.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "jwt.token")
@Getter
@Setter
public class JwtTokenProperty {
	private String secret = "secret";
	private int expiration = 60000;
}
