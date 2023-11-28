package edu.pnu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.BigTrash;
import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BigTrashRepository;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.CommentRepository;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.service.BoardService;
import edu.pnu.service.CommentService;
import jakarta.persistence.EntityManager;

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

	@PostMapping("/nowWrite")
	public ResponseEntity<?> postBoardList(@RequestBody String board) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();

		
		Board B = new Board();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(board);
			String username = jsonNode.get("username").asText();
			String title = jsonNode.get("title").asText();
			String content = jsonNode.get("content").asText();
			String tag = jsonNode.get("tag").asText();
			Integer bigId = jsonNode.get("bigId").asInt();

			Optional<Member> opM = memRepo.findByUsername(username);
			Optional<BigTrash> bts = bigRepo.findById(bigId);
			Optional<String> listob = bigRepo.findNameAndCateByBigId(bigId);
			String[] arr = listob.get().split(",");
			String name = arr[0];
			String cate = arr[1];
			
			
			
			if (opM.isPresent() && bts.isPresent()) {
				B.setUsername(opM.get());
				B.setTitle(title);
				B.setContent(content);
				B.setTag(tag);
				B.setBigId(bts.get());
				
				map.put("name", name);
				map.put("cate", cate);
				map.put("bigId", B.getBigId());
				map.put("postId", B.getPostId());
				map.put("username", B.getUsername());
				map.put("title", B.getTitle());
				map.put("content", B.getContent());
				map.put("image", B.getImage());
				map.put("count", B.getCount());
				map.put("tag", B.getTag());
				map.put("createDate", B.getCreateDate());
				boardRepo.save(B);
	
				
			} else {
				System.out.println("일치하는 유저가 존재하지 않음");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return ResponseEntity.ok().body(map);
	}

	@GetMapping("/nowList")
	public ResponseEntity<?> getBoardList(@RequestParam(required = false) String username) {
		Page<Board> boardList = boardService.getBoardList(username);
		return ResponseEntity.ok(boardList);
	}

	@GetMapping("/nowBoard")
	public ResponseEntity<?> getPostDetail(Integer postId, Integer bigId) {
		Map<String, List<?>> data = new HashMap<>();
		data.put("board", boardService.getPostDetail(postId));
		data.put("comment", CommService.getCommentByBoardSeq(postId));

		return ResponseEntity.ok().body(data);
	}
	
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
	
	@DeleteMapping("/delComment")
	public ResponseEntity<?> delComm(@RequestParam Integer commentId){
		CommService.deleteComm(commentId);
		return ResponseEntity.ok("댓글 삭제 성공");
	}
}
