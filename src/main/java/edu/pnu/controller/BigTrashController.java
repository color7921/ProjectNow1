package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BigTrash;
import edu.pnu.service.BigTrashService;

@RestController
@RequestMapping("/api/search")

// /api/search?sido=값&cate=값&keyword=값
public class BigTrashController {
	
	@Autowired
	private BigTrashService bigTrashService;
	
	//post - /api/login 로그인
	//post - /api/signup 로그아웃
	
	//@PostMapping("/test")
	public void test(@RequestBody BigTrash test) {
		System.out.println(test);
	}
	  
	@GetMapping
	public ResponseEntity<?> test(String sido, String cate, String keyword) {
		
		return ResponseEntity.ok().body(bigTrashService.getBigTrash(sido, cate, keyword));
	};
	

}
