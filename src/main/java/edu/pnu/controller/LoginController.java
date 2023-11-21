package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@RestController
public class LoginController {

	@Autowired
	MemberRepository memRepo ;
	
	@PostMapping("/signup")
	public void signup(@RequestBody Member member) {
		System.out.println(member);
		memRepo.save(member);
	
	}
}
