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
//	@Autowired
//	private BigTrashRepository trash
	
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getPostId()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	public void deleteBoard(Integer postId) {
		boardRepo.deleteById(postId);
	}

	public Board getBoard(Board board) {
		return boardRepo.findById(board.getPostId()).get();
	}

	public Page<Board> getBoardList(String username) {
		Pageable pageable = PageRequest.of(0, 25, Sort.Direction.DESC, "PostId");
		Page<Board> pageList = boardRepo.findAll(pageable);
		return pageList;
	}
	
	public List<Board> getPostDetail(Integer postId){
		List<Board> boardObjectList = boardRepo.findByPostId(postId);
		
		return boardObjectList;
	}
	
//	public getBigId(String sido, String cate, String name, String size) {
//		return bigRfindBySidoAndCateAndNameAndSize
//	}
}
