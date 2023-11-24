package edu.pnu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Comment;
import edu.pnu.persistence.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository CommRepo;
	
	public List<Comment> getCommentByBoardSeq(Integer postId){
		return CommRepo.findByPostId(postId);
		
		
		
	}
}
