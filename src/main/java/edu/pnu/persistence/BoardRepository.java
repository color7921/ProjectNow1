package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);
	List<Board> findByPostId(Integer postId);
	
	
	
}
