package edu.pnu.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.BigTrash;

public interface BigTrashRepository extends JpaRepository<BigTrash, Integer> {

	 List<BigTrash> findBySidoAndCateAndNameContaining(String sido, String cate, String keyword);	
	 List<BigTrash> findBySidoAndNameContaining(String sido, String keyword);
	 List<BigTrash> findByNameContaining(String keyword);
//	 @Query("Select distinct b.name from BigTrash b where cate like %:cate%")
//	 List<Object> findDistinctByCateContaining(String cate);
	 @Query("SELECT DISTINCT b.name FROM BigTrash b WHERE b.cate LIKE %:cate% AND b.sido LIKE %:sido%")
	 List<Object> findDistinctByCateContaining(String cate, String sido);
	 @Query("SELECT c.size FROM BigTrash c WHERE c.cate LIKE %:cate% AND c.sido LIKE %:sido% AND c.name LIKE %:name%")
	 List<String> findBySidoAndCateAndName(String cate, String sido, String name);
//	 List<Object> findByCateAndName(String cate, String name);
	 @Query(value = "select d.name, d.cate from bigtrash d where d.big_id = :bigId", nativeQuery = true)
	 Optional<String> findNameAndCateByBigId(@Param("bigId") Integer bigId);

}
