package momsitter.apply.user.domain.repo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import momsitter.apply.user.api.dto.ParentDto;
import momsitter.apply.user.domain.Parent;
import momsitter.apply.user.domain.repo.ParentRepository;

@Repository
public class JdbcParentRepository implements ParentRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert insertTemplate;

	public JdbcParentRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.insertTemplate = new SimpleJdbcInsert(jdbcTemplate);
		insertTemplate.withTableName("parent").usingGeneratedKeyColumns("no");
	}

	@Override
	public void create(ParentDto parent) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", parent.getUserId());
		params.put("request_msg", parent.getRequestMessage());

		Number number = insertTemplate.executeAndReturnKey(params);
		parent.setNo(number.longValue());
	}

	@Override
	public Optional<Parent> findByUserId(String userId) {
		List<Parent> parents = jdbcTemplate.query(
			"SELECT `no`, `user_id`, `request_msg` FROM `parent` WHERE `user_id` = ?",
			MAPPER,
			userId
		);
		return Optional.ofNullable(parents.isEmpty() ? null : parents.get(0));
	}

	private static final RowMapper<Parent> MAPPER = (rs, rowNum) ->
		Parent.builder()
			.no(rs.getLong("no"))
			.userId(rs.getString("user_id"))
			.requestMessage(rs.getString("request_msg"))
			.build();
}
