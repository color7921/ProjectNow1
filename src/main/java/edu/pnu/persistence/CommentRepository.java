package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	
	List<Comment> findByPostId(Integer postId);


}
