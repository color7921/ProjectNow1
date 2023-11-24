package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Comment;
import edu.pnu.persistence.CommentRepository;

@RestController
@RequestMapping("/api/user")
public class CommentController {

	@Autowired
	private CommentRepository commentRepo;
	
	@PostMapping("/commWrite")
	public ResponseEntity<?> postCommList(@RequestBody Comment comment){
		commentRepo.save(Comment.builder()
				
				.username(comment.getUsername())
				.postId(comment.getPostId())
				.commContent(comment.getCommContent())
				.build());
		return ResponseEntity.ok().build();
	}
	
}
