package edu.pnu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BigTrash;
import edu.pnu.domain.Board;
import edu.pnu.domain.Comment;
import edu.pnu.domain.Member;
import edu.pnu.domain.dto.BigTrashRequestDto;
import edu.pnu.domain.dto.BoardUpadateRequestDto;
import edu.pnu.domain.dto.CommentUpdateDto;
import edu.pnu.persistence.BigTrashRepository;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.CommentRepository;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.service.BoardService;
import edu.pnu.service.CommentService;

@RestController
@RequestMapping("/api/user")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private CommentRepository commRepo;
	@Autowired
	private CommentService CommService;
	@Autowired
	private MemberRepository memRepo;
	@Autowired
	private BigTrashRepository bigRepo;

	// 게시글 목록 출력 /api/user/nowList [ o ]
		@GetMapping("/nowList")
		public ResponseEntity<?> getBoardList(@RequestParam(required = false) String username) {
			List<Board> boardList = boardService.getBoardList(username);
			return ResponseEntity.ok(boardList);
		}
		
	// 게시글 목록 출력 (page 버전)
//		@GetMapping("/nowList")
//		public ResponseEntity<?> getBoardList(@RequestParam(required = false) String username) {
//			Page<Board> boardList = boardService.getBoardList(username);
//			return ResponseEntity.ok(boardList);
//		}

	// 게시글 등록 /api/user/nowWrite [ o ] 
	@PostMapping("/nowWrite")
	//requestbody - > multipart
	public ResponseEntity<?> postBoardList(@RequestBody BigTrashRequestDto bigTrashRequestDto, @AuthenticationPrincipal User user) {
		List<BigTrash> bigTrash = bigRepo.findBySidoAndCateAndNameAndSize(bigTrashRequestDto.getSido(), bigTrashRequestDto.getCate(), bigTrashRequestDto.getName(), bigTrashRequestDto.getSize());
		
		String name = user.getUsername();
		
		Member member = memRepo.findById(name).get();
		
		// Board.builder : 생성 (update에 사용 x)
		boardRepo.save(Board.builder()
				.member(member)
				.title(bigTrashRequestDto.getTitle())
				.content(bigTrashRequestDto.getContent())
				.image(bigTrashRequestDto.getImage())
				.tag(bigTrashRequestDto.getTag())
				.bigTrash(bigTrash.get(0))
				.build()); 
		return ResponseEntity.ok().build();
	}
	
	// username에 해당하는 상세 게시글 보내기
	@GetMapping("/mypage")
	public ResponseEntity<?> getUsernameList(String username){
		return ResponseEntity.ok().body(boardService.getUsernameList(username));
	}
	
	// 게시글 목록에서 키워드 보내기 /api/user/nowListSearch?keyword=? [ keyword == null 일 때 확인 ]
	@GetMapping("/nowListSearch")
	public ResponseEntity<?> getBoardKeyword(String keyword, Integer postId){
		return ResponseEntity.ok().body(boardService.getBoardKeyword(keyword, postId));
	}
	
	
	// 게시글 상세정보 보내기 /api/user/nowBoard?postId=? [ o ]
		@GetMapping("/nowBoard")
		public ResponseEntity<?> getPostDetail(Integer postId, Integer bigId) {
		
			return ResponseEntity.ok().body(boardService.getPostDetail(postId));
	}
		
	// 댓글 보내기 /api/user/nowComment?postId=? [ o ]
	@GetMapping("/nowComment")
	public ResponseEntity<?> getComment(Board board){
		
		return ResponseEntity.ok().body(CommService.getCommentByBoardSeq(board));
	}
	
	// 게시글 수정하기 localhost:8080/api/user/updateNow [ o ]
	@PutMapping("/updateNow")
	public ResponseEntity<?> updateBoard(@RequestBody BoardUpadateRequestDto boardUpdateDto){
	
		Optional<Board> existingBoard = boardRepo.findById(boardUpdateDto.getPostId());
		
		if (existingBoard.isPresent()) {
			Board updatedBoard = existingBoard.get();
			updatedBoard.setTitle(boardUpdateDto.getTitle());
			updatedBoard.setContent(boardUpdateDto.getContent());
			updatedBoard.setTag(boardUpdateDto.getTag());
			boardRepo.save(updatedBoard);
			return ResponseEntity.ok().build(); 
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
				
	// 댓글 수정하기 localhost:8080/api/user/updateComment [ o ]
	@PutMapping("/updateComment")
	public ResponseEntity<?> updateComment(@RequestBody CommentUpdateDto commentUpdateDto){
		
		 List<Board> boardName = boardRepo.findByPostId(commentUpdateDto.getPostId());
		 Optional<Comment> existingComment = commRepo.findById(commentUpdateDto.getCommentId());
		 
		 if (existingComment.isPresent()) {
			 Comment updatedComment = existingComment.get();
			 updatedComment.setCommContent(commentUpdateDto.getCommContent());
			 commRepo.save(updatedComment);
			 
			 List<Comment> partCommentList = commRepo.findByBoard(boardName.get(0));
			 return ResponseEntity.ok().body(partCommentList);
		 
		
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// 게시글을 삭제하면 댓글도 같이 삭제 /api/user/delBoard?postId=? [ o ]
	@DeleteMapping("/delBoard")
	public ResponseEntity<?> delBoard(@RequestParam Integer postId){
		try {
		boardService.deleteBoard(postId);
		CommService.deleteComm(postId);
		return ResponseEntity.ok("게시글, 댓글 삭제 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 오류");
		}
	}
	
	// 댓글 삭제 /api/user/delComment?commentId=? [ o ]
	@DeleteMapping("/delComment")
	public ResponseEntity<?> delComm(@RequestParam Integer commentId, Board board){
		CommService.deleteComm(commentId);
		return ResponseEntity.ok().body(CommService.getCommentByBoardSeq(board));
	}
}
