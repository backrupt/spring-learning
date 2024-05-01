package com.itwill.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.itwill.security.user.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = false)
public class SecurityConfig {
	/*@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};*/
	@Autowired
	UserDetailsService userDetailsService;

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeRequests) -> { 
			authorizeRequests.requestMatchers("/", "/authentication","/login").permitAll();
			authorizeRequests.anyRequest().authenticated();
		});  
		http.formLogin(Customizer.withDefaults());
		http.formLogin((formLoginConfig) -> {
			formLoginConfig.loginPage("/login")
						   .loginProcessingUrl("/login")
					       .defaultSuccessUrl("/", true)
					       .failureUrl("/login-error");

		});
		http.logout((formLogoutConfig) -> {
			formLogoutConfig.logoutUrl("/logout");
			formLogoutConfig.logoutSuccessUrl("/");
		});
		http.exceptionHandling((exceptionHandlingConfig) -> {
			exceptionHandlingConfig.accessDeniedPage("/access-denied");
		});
		http.userDetailsService(userDetailsService);
		
		return http.build();
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		return (webSecurityBuilder) -> {
			webSecurityBuilder.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		};
	}
}
