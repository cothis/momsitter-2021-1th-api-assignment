package momsitter.apply.user.service;

import java.util.Optional;

import javax.security.auth.login.FailedLoginException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import momsitter.apply.exceptions.NotFoundException;
import momsitter.apply.user.api.dto.ParentDto;
import momsitter.apply.user.api.dto.SitterDto;
import momsitter.apply.user.api.dto.UserDto;
import momsitter.apply.user.domain.Parent;
import momsitter.apply.user.domain.Sitter;
import momsitter.apply.user.domain.User;
import momsitter.apply.user.domain.repo.ChildrenRepository;
import momsitter.apply.user.domain.repo.ParentRepository;
import momsitter.apply.user.domain.repo.SitterRepository;
import momsitter.apply.user.domain.repo.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;
	private final SitterRepository sitterRepository;
	private final ParentRepository parentRepository;
	private final ChildrenRepository childrenRepository;
	private final PasswordEncoder passwordEncoder;

	public User login(String id, String password) throws FailedLoginException {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Could not found user " + id));
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		throw new FailedLoginException("Could not match password");
	}

	public UserDto findById(String id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Could not found user " + id));

		Optional<Sitter> sitter = sitterRepository.findByUserId(user.getId());
		Optional<Parent> parent = parentRepository.findByUserId(user.getId());
		parent.ifPresent(p -> p.setChildren(childrenRepository.findByParentNo(p.getNo())));

		return new UserDto(user, sitter, parent);
	}

	@Transactional
	public void join(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.create(userDto);

		createSitter(userDto);
		createParent(userDto);
	}

	private void createSitter(UserDto userDto) {
		SitterDto sitter = userDto.getSitter();
		if (sitter == null) {
			return;
		}

		sitter.setUserId(userDto.getId());
		sitterRepository.create(sitter);
		userDto.setSitter(sitter);
	}

	private void createParent(UserDto userDto) {
		ParentDto parent = userDto.getParent();
		if (parent == null) {
			return;
		}

		parent.setUserId(userDto.getId());
		parentRepository.create(parent);
		childrenRepository.create(parent.getNo(), parent.getChildren());
		userDto.setParent(parent);
	}
}
