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

	// 게시글 목록 출력
	public List<Board> getBoardList(String username) {
		List<Board> boardList = boardRepo.findAll();
		return boardList;
	}

	// 게시글 목록 출력 (page 버전)
//	public Page<Board> getBoardList(String username) {
//		Pageable pageable = PageRequest.of(0, 25, Sort.Direction.DESC, "PostId");
//		Page<Board> pageList = boardRepo.findAll(pageable);
//		return pageList;
//	}
	
	// 게시판 목록에서 키워드에 해당하는 게시글 보내기
		public List<Board> getBoardKeyword(String keyword, Integer postId){
			
			// keyword가 없을때 = 전체 게시글 띄우기
			if (keyword.equals("전체")) {
				return boardRepo.findAll();
			} else {
				return boardRepo.findByTitleContaining(keyword);
			}
		}
		
	// 게시판 상세정보 보내기
	public List<Board> getPostDetail(Integer postId){
		
		boardRepo.updateCountByPostId(postId);
		List<Board> boardObjectList = boardRepo.findByPostId(postId);
		return boardObjectList;
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
