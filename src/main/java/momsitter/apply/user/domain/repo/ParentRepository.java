package momsitter.apply.user.domain.repo;

import java.util.Optional;

import momsitter.apply.user.api.dto.ParentDto;
import momsitter.apply.user.domain.Parent;

public interface ParentRepository {
	Optional<Parent> findByUserId(String userId);

	void create(ParentDto parent);
}
