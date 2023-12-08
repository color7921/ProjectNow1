package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardService  {
	
	@Autowired
	private BoardRepository boardRepo;

	// 게시글 목록 출력 (다 보여주는 버전)
//	public List<Board> getBoardList(String username) {
//		List<Board> boardList = boardRepo.findAll();
//		return boardList;
//	}
	
	// 게시글 목록 출력 (page 버전)
	public Page<Board> getBoardList(Integer pageNo) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, 15, Sort.Direction.DESC, "postId");
		Page<Board> pageList = boardRepo.findAll(pageable);
		pageList.forEach(b -> b.setImage(null));
		
		return pageList;
	}
	
	// 게시판 상세정보 보내기
	public Board getBoard(Integer postId) {
		
		Board board = boardRepo.findByPostId(postId);
		board.setCount(board.getCount()+1);
		boardRepo.save(board);
		return board;
	}
	
	// 게시판 목록에서 키워드에 해당하는 게시글 보내기
	public List<Board> getBoardKeyword(String keyword, Integer postId){
		
		// keyword가 없을때 = 전체 게시글 띄우기
		if (keyword.equals("전체")) {
			return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "postId"));
		} else {
			return boardRepo.findByTitleContainingOrderByPostIdDesc(keyword);
		}
	}
		
	// 특정 유저가 쓴 게시글 전부 보내기
	public List<Board> getUsernameList(String username){
		
		List<Board> usernameList = boardRepo.findByMemberUsername(username);
		return usernameList;
	}
		
	// 게시글 수정
	public void updateBoard(Board board) {
		
		Board findBoard = boardRepo.findById(board.getPostId()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	// 게시글 삭제
	public void deleteBoard(Integer postId) {
		boardRepo.deleteById(postId);
	}

}
