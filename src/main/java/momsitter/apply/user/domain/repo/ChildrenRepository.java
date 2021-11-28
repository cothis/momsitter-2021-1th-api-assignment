package momsitter.apply.user.domain.repo;

import java.util.List;

import momsitter.apply.user.api.dto.ChildrenDto;
import momsitter.apply.user.domain.Children;

public interface ChildrenRepository {
	List<Children> findByParentNo(Long parentNo);

	void create(Long parentNo, List<ChildrenDto> children);

	void update(Long parentNo, List<ChildrenDto> children);
}
