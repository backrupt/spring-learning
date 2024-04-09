package com.itwill.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itwill.user.controller.AuthLoginAnnotationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	/********************WebMvcConfigurer재정의*********************
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.jsp");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		 registry.jsp("/WEB-INF/views/",".jsp");
	}
	**/
	/*
	 * 인터셉터등록
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		AuthLoginInterceptor authLoginInterceptor=new AuthLoginInterceptor();
		registry.addInterceptor(authLoginInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/css/**")
		.excludePathPatterns("/js/**")
		.excludePathPatterns("/image/**")
		.excludePathPatterns("/user_main")
		.excludePathPatterns("/user_login_form")
		.excludePathPatterns("/user_login_action")
		.excludePathPatterns("/user_write_form")
		.excludePathPatterns("/user_write_action");
		*/
		
		AuthLoginAnnotationInterceptor authLoginAnnotationInterceptor=
				new AuthLoginAnnotationInterceptor();
		registry.addInterceptor(authLoginAnnotationInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/css/**")
		.excludePathPatterns("/js/**")
		.excludePathPatterns("/image/**");
		
	}
	/*************************************************************/
	
	
	
	/*********************Spring MVC 빈객체등록*********************/
	// 1.ViewResolver객체등록
	/*
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver=new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);
		return beanNameViewResolver;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}
	*/
	
	
}
