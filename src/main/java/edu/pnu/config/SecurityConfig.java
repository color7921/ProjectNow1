package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		http.cors(cors->cors.configurationSource(corsFilter()));
		http.formLogin(frmLogin->frmLogin.disable());
		// api/user의 주소로 시작하는 페이지는 로그인 권한이 필요함.
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(new AntPathRequestMatcher("/api/user/**")).authenticated()
				.anyRequest().permitAll());
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));	
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		http.addFilter(new JWTAuthenticationFilter(
				authenticationConfiguration.getAuthenticationManager()));	
		return http.build();
	}

    @Bean
    CorsConfigurationSource corsFilter() {
    	
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://localhost:3000"); // 교차를 허용할 Origin
	    config.addAllowedOrigin("http://10.125.121.215:3000");
	    config.addAllowedOriginPattern("*");
	    config.addAllowedMethod(CorsConfiguration.ALL); // 교차를 허용할 Method
	    config.addAllowedHeader(CorsConfiguration.ALL); // 교차를 허용할 Header
	    config.setAllowCredentials(true); // 요청/응답에 자격증명정보 포함을 허용
	    config.addExposedHeader("Authorization");
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config); // 교차를 허용할 Origin의 URL
	    return source;
    }
    
}