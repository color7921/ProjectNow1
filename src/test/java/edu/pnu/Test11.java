package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BigTrashRepository;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.service.BoardService;

@SpringBootTest
public class Test11 {

	@Autowired
	BigTrashRepository bigTrashRepository;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	BoardService boardService;
	
	 @Test
	 public void testGetBoard() {
		Page<Board> list = boardService.getBoardList(null);
		System.out.println("##############" + list);
		list.forEach(b -> {
				b.setImage(null);
				System.out.println(b);
			}
		);
	}
}
