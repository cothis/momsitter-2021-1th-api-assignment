package momsitter.apply.user.domain.repo;

import java.util.Optional;

import momsitter.apply.user.api.dto.SitterDto;
import momsitter.apply.user.domain.Sitter;

public interface SitterRepository {
	Optional<Sitter> findByUserId(String userId);

	void create(SitterDto sitter);
}
