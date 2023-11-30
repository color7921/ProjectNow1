package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pnu.domain.BigTrash;

public interface BigTrashRepository extends JpaRepository<BigTrash, Integer> {

	 // 1. BigTrashService의 getBigTrash 메서드에 활용
	 List<BigTrash> findBySidoAndCateAndNameContaining(String sido, String cate, String keyword);	
	 List<BigTrash> findBySidoAndNameContaining(String sido, String keyword);
	 
	 // 2. BigTrashService의 getCate 메서드에 활용
	 @Query("SELECT DISTINCT a.name FROM BigTrash a WHERE a.sido Like %:sido%")
	 List<Object> findByNameAndSido(String sido);
	 @Query("SELECT DISTINCT b.name FROM BigTrash b WHERE b.cate LIKE %:cate% AND b.sido LIKE %:sido%")
	 List<Object> findDistinctByCateContaining(String cate, String sido);
	 
	 // 3. BigTrashService의 getName 메서드에 활용
	 @Query("SELECT c.size FROM BigTrash c WHERE c.cate LIKE %:cate% AND c.sido LIKE %:sido% AND c.name LIKE %:name%")
	 List<String> findBySidoAndCateAndName(String cate, String sido, String name);
	 
	 // BoardController의 게시글 등록에 활용
	 List<BigTrash> findBySidoAndCateAndNameAndSize(String sido, String cate, String name, String size);
	 
}
