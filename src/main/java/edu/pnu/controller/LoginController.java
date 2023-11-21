package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@RestController
public class LoginController {

	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/signup")
	public void signup(@RequestBody Member member) {
		System.out.println(member);
		memRepo.save(Member.builder()
			.username(member.getUsername())
			.password(encoder.encode(member.getPassword()))
			.build());
	}
}
