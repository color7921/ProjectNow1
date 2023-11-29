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
	 
	 @Query("SELECT DISTINCT b.name FROM BigTrash b WHERE b.cate LIKE %:cate% AND b.sido LIKE %:sido%")
	 List<Object> findDistinctByCateContaining(String cate, String sido);
	 
	 @Query("SELECT c.size FROM BigTrash c WHERE c.cate LIKE %:cate% AND c.sido LIKE %:sido% AND c.name LIKE %:name%")
	 List<String> findBySidoAndCateAndName(String cate, String sido, String name);
//	 List<Object> findByCateAndName(String cate, String name);

	 
//	 @Query(value = "SELECT d.cate, d.size FROM bigtrash d WHERE d.bigId = :bigID")
//	 @Query(value = "SELECT d.bigId FROM bigtrash d WHERE d.sido = :sido AND d.cate = :cate AND d.name = :name AND d.size = :size", nativeQuery = true)
//	 Optional<String> findNameAndCateByBigId(@Param("bigId") Integer bigId);

}
