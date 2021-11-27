package momsitter.apply.user.domain.repo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import momsitter.apply.user.api.dto.UserDto;
import momsitter.apply.user.domain.User;
import momsitter.apply.user.domain.repo.UserRepository;
import momsitter.apply.utils.DateTimeUtil;

@Repository
@Slf4j
public class JdbcUserRepository implements UserRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert insertTemplate;

	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.insertTemplate = new SimpleJdbcInsert(jdbcTemplate);
		insertTemplate.withTableName("user").usingGeneratedKeyColumns("no");
	}

	@Override
	public Optional<User> findById(String id) {
		List<User> users = jdbcTemplate.query(
			"SELECT * FROM USER WHERE ID = ?",
			MAPPER,
			id);
		return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
	}

	@Override
	public Optional<User> login(String id, String password) {
		List<User> users = jdbcTemplate.query(
			"SELECT * FROM USER WHERE ID = ? AND PASSWORD = ?",
			MAPPER,
			id,
			password);
		return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
	}

	@Override
	public void create(UserDto userDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", userDto.getName());
		params.put("birthday", DateTimeUtil.timestampOf(userDto.getBirthday()));
		params.put("gender", userDto.getGender());
		params.put("id", userDto.getId());
		params.put("password", userDto.getPassword());
		params.put("email", userDto.getEmail());

		Number number = insertTemplate.executeAndReturnKey(params);
		userDto.setNo(number.longValue());
	}

	private static final RowMapper<User> MAPPER = (rs, rowNum) ->
		User.builder()
			.no(rs.getLong("no"))
			.name(rs.getString("name"))
			.birthday(DateTimeUtil.dateTimeOf(rs.getTimestamp("birthday")))
			.gender(rs.getString("gender"))
			.id(rs.getString("id"))
			.password(rs.getString("password"))
			.email(rs.getString("email"))
			.build();
}
