package momsitter.apply.user.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import momsitter.apply.security.JwtUser;
import momsitter.apply.user.api.dto.UserDto;
import momsitter.apply.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
		userService.join(userDto);

		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping("/me")
	public UserDto me(HttpServletRequest request) {
		JwtUser user = (JwtUser)request.getAttribute("user");
		return userService.findById(user.getId());
	}
}
