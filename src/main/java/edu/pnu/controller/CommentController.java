package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.persistence.CommentRepository;

@RestController
@RequestMapping("/api/user")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	
//	@PostMapping("/commWrite")
//	
//	
}
