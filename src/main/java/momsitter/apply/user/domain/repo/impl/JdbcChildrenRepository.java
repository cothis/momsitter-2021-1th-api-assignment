package momsitter.apply.user.domain.repo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import momsitter.apply.user.api.dto.ChildrenDto;
import momsitter.apply.user.domain.Children;
import momsitter.apply.user.domain.repo.ChildrenRepository;
import momsitter.apply.utils.DateTimeUtil;

@Repository
public class JdbcChildrenRepository implements ChildrenRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert insertTemplate;

	public JdbcChildrenRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.insertTemplate = new SimpleJdbcInsert(jdbcTemplate);
		insertTemplate.withTableName("children").usingGeneratedKeyColumns("no");
	}

	@Override
	public void create(Long parentNo, List<ChildrenDto> children) {
		for (ChildrenDto child : children) {
			Map<String, Object> params = new HashMap<>();
			params.put("parent_no", parentNo);
			params.put("birthday", child.getBirthday());
			params.put("gender", child.getGender());
			Number number = insertTemplate.executeAndReturnKey(params);

			child.setNo(number.longValue());
		}
	}

	@Override
	public List<Children> findByParentNo(Long parentNo) {
		return jdbcTemplate.query(
			"SELECT `no`, `parent_no`, `birthday`, `gender` FROM `children` WHERE `parent_no` = ?",
			MAPPER,
			parentNo
		);
	}

	@Override
	public void delete(Long parentNo) {
		jdbcTemplate.update(
			"DELETE FROM `children` WHERE `parent_no` = ?",
			parentNo
		);
	}

	private final RowMapper<Children> MAPPER = (rs, rowNum) ->
		Children.builder()
			.no(rs.getLong("no"))
			.parentNo(rs.getLong("parent_no"))
			.birthday(DateTimeUtil.dateTimeOf(rs.getTimestamp("birthday")))
			.gender(rs.getString("gender"))
			.build();
}
