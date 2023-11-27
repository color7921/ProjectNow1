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
@RequestMapping("/api")

// /api/search?sido=값&cate=값&keyword=값
public class BigTrashController {
	
	@Autowired
	private BigTrashService bigTrashService;
	
	
	//@PostMapping("/test")
	public void test(@RequestBody BigTrash test) {
		System.out.println(test);
	}
	
	//api/wastename?sido=?&cate=?
	@GetMapping("/wastename")
	public ResponseEntity<?> cateTest(String cate, String sido){
		return ResponseEntity.ok().body(bigTrashService.getCate(cate, sido));
	}
	  
	@GetMapping("/search")
	public ResponseEntity<?> test(String sido, String cate, String keyword) {
		
		return ResponseEntity.ok().body(bigTrashService.getBigTrash(sido, cate, keyword));
	}
	
	//api/wastesize?sido=?&cate=?name=?
	@GetMapping("/wastesize")
	public ResponseEntity<?> nameTest(String sido, String cate, String name){
		return ResponseEntity.ok().body(bigTrashService.getName(cate, sido, name));
	}
}
