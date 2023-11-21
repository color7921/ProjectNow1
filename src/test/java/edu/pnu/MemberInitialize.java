package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class MemberInitialize {
	@Autowired
	private MemberRepository memRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void memberInitialize() {
		memRepo.save(Member.builder().username("member").password(encoder.encode("abcd"))
				.build());
		memRepo.save(Member.builder().username("manager").password(encoder.encode("abcd"))
				.build());
		memRepo.save(Member.builder().username("admin").password(encoder.encode("abcd"))
				.build());
	}
}
