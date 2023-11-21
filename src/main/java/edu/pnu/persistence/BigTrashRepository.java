package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.BigTrash;

public interface BigTrashRepository extends JpaRepository<BigTrash, Integer> {

	 List<BigTrash> findBySidoAndCateAndNameContaining(String sido, String cate, String keyword);	
	 List<BigTrash> findBySidoAndNameContaining(String sido, String keyword);
	 List<BigTrash> findByNameContaining(String keyword);
}
