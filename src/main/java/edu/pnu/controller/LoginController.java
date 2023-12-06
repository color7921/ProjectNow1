package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@RestController

public class LoginController {

	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(){
		return ResponseEntity.ok("ok");
	}
	
	@PostMapping("/api/signup")
	public ResponseEntity<?> signup(@RequestBody Member member) {
		System.out.println(member);

		try {
			if (memRepo.existsById(member.getUsername())) {
				
				return ResponseEntity.notFound().build();
			} else {
				memRepo.save(Member.builder()
						.username(member.getUsername())
						.password(encoder.encode(member.getPassword()))
						.build());
				return ResponseEntity.ok().build();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
