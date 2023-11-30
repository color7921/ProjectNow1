package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.BigTrashService;

@RestController
@RequestMapping("/api")
public class BigTrashController {
	
	@Autowired
	private BigTrashService bigTrashService;
	
	// 1. sido, cate, keyword에 해당하는 값 전송 /api/search?sido=값&cate=값&keyword=값
	@GetMapping("/search")
	public ResponseEntity<?> test(String sido, String cate, String keyword) {
		return ResponseEntity.ok().body(bigTrashService.getBigTrash(sido, cate, keyword));
	}
	
	// 2. sido, cate에 해당하는 값 전송 api/wastename?sido=?&cate=?
	@GetMapping("/wastename")
	public ResponseEntity<?> cateTest(String cate, String sido){
		return ResponseEntity.ok().body(bigTrashService.getCate(cate, sido));
	}
	  
	// 3. sido, cate, name에 해당하는 값 전송 api/wastesize?sido=?&cate=?name=?
	@GetMapping("/wastesize")
	public ResponseEntity<?> nameTest(String sido, String cate, String name){
		return ResponseEntity.ok().body(bigTrashService.getName(cate, sido, name));
	}
	
}
