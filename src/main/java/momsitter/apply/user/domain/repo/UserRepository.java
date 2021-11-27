package momsitter.apply.user.domain.repo;

import java.util.Optional;

import momsitter.apply.user.api.dto.UserDto;
import momsitter.apply.user.domain.User;

public interface UserRepository {

	Optional<User> findById(String id);

	Optional<User> login(String id, String password);

	void create(UserDto userDto);
}
