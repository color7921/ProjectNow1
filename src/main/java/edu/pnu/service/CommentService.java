package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.domain.Comment;
import edu.pnu.persistence.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository CommRepo;
	
	public List<Comment> getAllComment(){
		return CommRepo.findAll();
	}

	public List<Comment> getCommentByBoardSeq(Board board){
		return CommRepo.findByBoard(board);
	}
	public void deleteComm(Integer postId) {
		CommRepo.deleteById(postId);
	}
	public void deleteComm2(Integer commentId) {
		CommRepo.deleteById(commentId);
	}
}
