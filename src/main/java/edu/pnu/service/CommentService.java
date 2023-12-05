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
	
//	public List<Comment> getAllComment(){
//		return CommRepo.findAll();
//	}

	// BoardController의 댓글 삭제
	public List<Comment> getCommentByBoardSeq(Board board){
		return CommRepo.findByBoard(board);
	}
	// 위와 상동
	public void deleteComm(Integer postId) {
		CommRepo.deleteById(postId);
	}
	
	// Member의 사용자가 썼던 Comment를 불러오기
	public List<Comment> getCommentByUsernameList(String username){
		return CommRepo.findByMemberUsername(username);
	}

}
