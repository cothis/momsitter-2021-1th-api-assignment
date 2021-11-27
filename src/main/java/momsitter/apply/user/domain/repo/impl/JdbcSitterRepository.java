package momsitter.apply.user.domain.repo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import momsitter.apply.user.api.dto.SitterDto;
import momsitter.apply.user.domain.Sitter;
import momsitter.apply.user.domain.repo.SitterRepository;

@Repository
public class JdbcSitterRepository implements SitterRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert insertTemplate;

	public JdbcSitterRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.insertTemplate = new SimpleJdbcInsert(jdbcTemplate);
		insertTemplate.withTableName("sitter").usingGeneratedKeyColumns("no");
	}

	@Override
	public Optional<Sitter> findByUserId(String userId) {
		List<Sitter> sitters = jdbcTemplate.query(
			"SELECT * FROM SITTER WHERE USER_ID = ?",
			MAPPER,
			userId
		);

		return Optional.ofNullable(sitters.isEmpty() ? null : sitters.get(0));
	}

	@Override
	public void create(SitterDto sitter) {
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", sitter.getUserId());
		params.put("intro_msg", sitter.getIntroMessage());
		params.put("care_from", sitter.getCareFrom());
		params.put("care_to", sitter.getCareTo());

		Number number = insertTemplate.executeAndReturnKey(params);
		sitter.setNo(number.longValue());
	}

	private static final RowMapper<Sitter> MAPPER = (rs, rowNum) ->
		Sitter.builder()
			.no(rs.getLong("no"))
			.userId(rs.getString("user_id"))
			.introMessage(rs.getString("intro_msg"))
			.careFrom(rs.getInt("care_from"))
			.careTo(rs.getInt("care_to"))
			.build();
}
