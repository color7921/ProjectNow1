package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.pnu.domain.Board;

// ※ 데이터베이스에 없는 컬럼 사용 주의


public interface BoardRepository extends JpaRepository<Board, Integer> {

	// BoardService의 getBoardKeyword 메서드에 활용
	List<Board> findByTitleContaining(String keyword);
	List<Board> findByPostId(Integer postId);
	
	// 쿼리를 작성하고 게시글을 클릭했을 때 조회수 증가하는 방법
	// update/delete 메서드 실행시 DB의 동일성을 유지하기위해 @Transactional 어노테이션을 사용한다
	// JPA에서 update/delete 메서드 실행시 excuteUpdate()메서드로 바꾸어 실행하기 위해 @Modifying 어노테이션을 삽입한다
	@Transactional
	@Modifying
	@Query(value = "update board set count = count + 1 where post_id = :postId ", nativeQuery = true)
	int updateCountByPostId(@Param("postId") Integer postId);

}
