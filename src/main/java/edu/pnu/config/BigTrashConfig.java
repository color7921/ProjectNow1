//package edu.pnu.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class BigTrashConfig {
//	
//	    //@Bean
//	    //SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//	    //    http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//	    //    return http.build();
//	    //}
//	    
//	    //@Bean
//	    CorsFilter corsFilter() {
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.addAllowedOrigin("http://localhost:3000"); // 교차를 허용할 Origin
//	    config.addAllowedMethod(CorsConfiguration.ALL); // 교차를 허용할 Method
//	    config.addAllowedHeader(CorsConfiguration.ALL); // 교차를 허용할 Header
//	    config.setAllowCredentials(true); // 요청/응답에 자격증명정보 포함을 허용
//	    config.addExposedHeader("Authorization");
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", config); // 교차를 허용할 Origin의 URL
//	    return new CorsFilter(source);
//	    }
//}
