package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.domain.Comment;
import edu.pnu.domain.Member;
import edu.pnu.domain.dto.CommentRequestDto;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.CommentRepository;
import edu.pnu.persistence.MemberRepository;

@RestController
@RequestMapping("/api/user")
public class CommentController {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private MemberRepository memRepo;
	@Autowired
	private BoardRepository boardRepo;

	@PostMapping("/commWrite")
	public ResponseEntity<?> postCommList(@RequestBody CommentRequestDto commDto, @AuthenticationPrincipal User user) {
		List<Member> memberName = memRepo.findByUsername(commDto.getUsername());
		List<Board> boardName = boardRepo.findByPostId(commDto.getPostId());
		
			commentRepo.save(Comment.builder()
					.member(memberName.get(0))
					.board(boardName.get(0))
					.commContent(commDto.getCommContent())
					.build());

			
			//ManyToOne fetch 옵션 FetchType.EAGER 기본값으로 설정되어 Comment 엔티티 조회 시 무조건 Post 객체를 가져옴
			List<Comment> comments = commentRepo.findByBoard(boardName.get(0));
			return ResponseEntity.ok().body(comments);
		} 
	}

