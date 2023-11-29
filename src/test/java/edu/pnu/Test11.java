package edu.pnu;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BigTrashRepository;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class Test11 {

	@Autowired
	BigTrashRepository bigTrashRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	//@Test는 매개변수를 가질 수 없음
	 @Test
	 public void testGetBoard() {
		 
		 Integer postId = 1;
		 Optional<Board> optionalBoard = boardRepository.findById(postId);

		 if (optionalBoard.isPresent()) {
			 Board findBoard = optionalBoard.get();
			 System.out.println("[ " + findBoard.getPostId() + " ]");
//			 System.out.println("제목" + findBoard.getUsername());
			 
		 }else {
			 System.out.println("찾을 수 없음");
		 }
	 }
}
