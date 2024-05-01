package com.itwill.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(User.builder().username("user2").password(passwordEncoder().encode("2222")).roles("USER"))
			.withUser(User.builder().username("admin").password(passwordEncoder().encode("3333")).roles("ADMIN"));
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeHttpRequests().anyRequest().permitAll();
		http.authorizeHttpRequests().antMatchers("/","/auth").permitAll();
		http.authorizeHttpRequests().anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login-error");
		
		http.logout().logoutSuccessUrl("/");
		http.exceptionHandling().accessDeniedPage("/access-denied");
		
		http.httpBasic();
		/*
		http.formLogin().disable()
			.httpBasic().disable()
			.cors().disable()
			.csrf().disable()
			.headers().disable()
			.anonymous().disable()
			.logout().disable()
			.requestCache().disable()
			.sessionManagement().disable()
			.securityContext().disable()
			.exceptionHandling().disable();
		*/	
			
	}
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
}
