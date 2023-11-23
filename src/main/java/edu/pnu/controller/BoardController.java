package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.service.BoardService;

@RestController
@RequestMapping("/api/user")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardRepository boardRepo;
	
		@PostMapping("/nowWrite")
		public ResponseEntity<?> postBoardList(@RequestBody Board board){
			boardRepo.save(Board.builder()
					
					.username(board.getUsername())
					.title(board.getTitle())
					.content(board.getContent())
					.image(board.getImage())
					.count(board.getCount())
					.tag(board.getTag())
					.name(board.getName())
					.cate(board.getCate())
					
					.build());
			return ResponseEntity.ok().build();
		}

		@GetMapping("/nowList")
		public ResponseEntity<?> getBoardList(@RequestParam(required =false) String username) {
			Page<Board> boardList = boardService.getBoardList(username);
			return ResponseEntity.ok(boardList);
		}
		
		@GetMapping("/nowBoard")
		public ResponseEntity<?> getPostDetail(Integer postId){
			return ResponseEntity.ok().body(boardService.getPostDetail(postId));
		}
}
