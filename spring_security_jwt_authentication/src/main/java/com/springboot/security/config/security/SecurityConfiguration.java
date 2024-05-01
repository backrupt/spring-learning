package com.springboot.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 어플리케이션의 보안 설정
 */
@Configuration
// Spring Security에 대한 디버깅 모드를 사용하기 위한 어노테이션 (default : false)
@EnableWebSecurity(debug = true)
@PropertySource("jwt.properties")
public class SecurityConfiguration {
	/**
	 * Swagger 페이지 접근에 대한 예외 처리
	 *
	 * @param webSecurity
	 */
	public static final String[] SwaggerPatterns = {
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/v3/api-docs/**",
			"/v3/api-docs",
			"/swagger-ui.html"
	};
	
	private final JwtTokenProvider jwtTokenProvider;

	public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic((httpBasicConfig) -> {
			// REST API는 UI를 사용하지 않으므로 기본설정을 비활성화
			httpBasicConfig.disable();
		});
		httpSecurity.csrf((csrfConfig) -> {
			// REST API는 csrf 보안이 필요 없으므로 비활성화
			csrfConfig.disable();
		});
		httpSecurity.sessionManagement((sessionManagementConfig) -> {
			// JWT Token 인증방식으로 세션은 필요 없으므로 비활성화
			sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});
		httpSecurity.authorizeHttpRequests((authorizeHttpRequestsConfig) -> {
			//swagger설정
			authorizeHttpRequestsConfig.requestMatchers(SwaggerPatterns).permitAll();
			// 가입 및 로그인 주소는 허용
			authorizeHttpRequestsConfig.requestMatchers("/user/join",
														"/user/login",
														"/user/exception")
					.permitAll();
			// product로 시작하는 Get 요청은 허용
			authorizeHttpRequestsConfig.requestMatchers(HttpMethod.GET, "/product/**").permitAll();
			authorizeHttpRequestsConfig.requestMatchers("**exception**").permitAll();
			// 나머지 요청은 인증된 ADMIN만 접근 가능
			authorizeHttpRequestsConfig.anyRequest().hasAnyRole("ADMIN");
		});
		httpSecurity.exceptionHandling((exceptionHandlingConfig) -> {
			exceptionHandlingConfig.accessDeniedHandler(new CustomAccessDeniedHandler());
			exceptionHandlingConfig.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		});
		// JWT Token 필터를 id/password 인증 필터 이전에 추가
		httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
				UsernamePasswordAuthenticationFilter.class);
		/*
		 * httpSecurity.authenticationManager();
		 * httpSecurity.authenticationManager();
		 */
		return httpSecurity.build();
	}

	/**
	 * Swagger 페이지 접근에 대한 예외 처리
	 *
	 * @param webSecurity
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (webSecurity) -> {
			webSecurity.ignoring().requestMatchers(SwaggerPatterns);
			webSecurity.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		};
	}
	
}