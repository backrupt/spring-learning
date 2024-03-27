package com.itwill.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
/*
 * web.xml설정을 대신할 클래스
 * -  톰캣실행시 기본설정
 */
@Configuration
public class WebAppInitConf implements ServletContextInitializer {
	public WebAppInitConf() {
		System.out.println("### WebApplicationInitializer()생성자");
	}
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
				
				System.out.println("### WebApplicationInitializer.onStartup()");
				/**********1.DispatcherServlet등록***************
				AnnotationConfigWebApplicationContext context = 
						new AnnotationConfigWebApplicationContext();
				ServletRegistration.Dynamic dynamic=
						servletContext.addServlet("dispatcherSevlet", new DispatcherServlet(context));
				dynamic.setLoadOnStartup(0);
				dynamic.addMapping("/");
				***************************************************/
				
				/********2.config 설정 클래스[ApplicationConfig.java,WebConfig.java]를사용한설정******
				//ApplicationConfig.java
				AnnotationConfigWebApplicationContext applicationContext = 
						new AnnotationConfigWebApplicationContext();
				applicationContext.register(ApplicationConfig.class);
				//Listener등록
				servletContext.addListener(new ContextLoaderListener(applicationContext));
				//WebConfig.java
				AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
				servletConfig.register(WebConfig.class);
				ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(servletConfig));
				dispatcherServlet.setLoadOnStartup(1);
				dispatcherServlet.addMapping("/");
				//encodingFilter설정
				FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter("UTF-8", true));
				filterRegistration.addMappingForUrlPatterns(null, true, "/*");
				*/
	}
	
	

	
}
