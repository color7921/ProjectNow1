//package edu.pnu.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.Authentication;
//
//import edu.pnu.domain.Member;
//
//public class LoginService {
//	@Autowired
//	private AuthenticationConfiguration authenticationConfiguration;
//
//	public String loginProc(Member member) throws Exception {
//	// Security에게 로그인 요청에 필요한 객체 생성
//	Authentication authToken = new UsernamePasswordAuthenticationToken(
//	member.getUsername(), member.getPassword());
//	// 로그인을 실행하는 Security 객체 생성
//	AuthenticationManager authenticationManager = 
//	authenticationConfiguration.getAuthenticationManager();
//	// 인증 진행 → UserDetailsService를 상속받은 클래스의 loadUserByUsername 호출한다.
//	Authentication auth = authenticationManager.authenticate(authToken);
//	System.out.println("auth:" + auth);
//	return "login"; // 인증 토큰 대신 임시로 리턴
//	}
//}
