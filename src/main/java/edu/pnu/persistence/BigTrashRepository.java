package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pnu.domain.BigTrash;

public interface BigTrashRepository extends JpaRepository<BigTrash, Integer> {

	 List<BigTrash> findBySidoAndCateAndNameContaining(String sido, String cate, String keyword);	
	 List<BigTrash> findBySidoAndNameContaining(String sido, String keyword);
	 List<BigTrash> findByNameContaining(String keyword);
	 @Query("Select distinct b.name from BigTrash b where cate like %:cate%")
	 List<Object> findDistinctByCateContaining(String cate);
}
