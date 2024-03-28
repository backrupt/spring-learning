package com.itwill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import com.itwill.view.XMLView;

@Controller
public class SpringResponseController {
    /*##################forward###########################*/
	
	/*
	 * fowarding view객체반환
	 */
	@RequestMapping("/response_forward_view_object")
	public View response_forward_view_object() {
		
		/*
		 * 1. Controller가 InternalResourceView 객체(URL:/WEB-INF/views/response_forward_view_object.jsp)반환
		 * 2. DispatcherServlet은 반환받은 View(InternalResourceView)객체의 renderMergedOutputModel 메쏘드호출
		 *      
		 *      protected void renderMergedOutputModel(	Map<String, Object> model, 
		 *      										HttpServletRequest request, 
		 *      										HttpServletResponse response) throws Exception;
		 * 
		 * 3. renderMergedOutputModel 메쏘드에서는 /WEB-INF/views/response_forward_view_object.jsp로 forward
		 * 
		 * 
		 */
		InternalResourceView internalResourceView=new InternalResourceView();
		internalResourceView.setUrl("/WEB-INF/views/response_forward_view_object.jsp");
		return internalResourceView;
	}
	 @GetMapping("/response_forward_view_name")
	 public String response_forward_view_name() {
		 /*
		  0 . Controller 가 view name(String)을 반환
		  1 . DispatcherServlet객체는  View객체를 얻기위해 ViewResolver객체를 찾는다.
		  
		  2 . 현재생성된  ViewResolver객체는 InternalResourceViewResolver 객체만 존재하므로 
		  	  InternalResourceViewResolver객체에 Controller로부터 반환받은 view name(String)을 주고 View객체를 요청한다.
		  3 . InternalResourceViewResolver 객체는 view name 이 forward: 로시작하므로(redirect:로시작하지않으면)  
		  		InternalResourceView 객체를 생성하고 view name을 url로 설정한후반환한다.
		      (url:/WEB-INF/views/response_view_name.jsp)를 반환
		  4 . DispatcherServlet객체는 반환받은 InternalResourceView 객체의 renderMergedOutputModel()  메쏘드호출한다: 	
			  (/WEB-INF/views/response_view_name.jsp 로 forward가이루어진다)	
			  
		   /***********WebConfig.java***********
		 	1. WebConfig.java파일에 InternalResourceViewResolver등록 
		 	
		 	@Bean
			public InternalResourceViewResolver internalResourceViewResolver() {
				InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
				internalResourceViewResolver.setPrefix("/WEB-INF/views/");
				internalResourceViewResolver.setSuffix(".jsp");
				internalResourceViewResolver.setOrder(1);
				return internalResourceViewResolver;
			}	
		
		
			< Controller반환 String >>
				1.forward:/WEB-INF/views/response_view_name.jsp  ==> prefix,suffix설정적용안됨
			    2.response_forward_view_name   					==> prefix,suffix설정적용됨
			    3.redirect:xxx.jsp     							==> prefix,suffix설정적용안됨
		 */
		 return "forward:/WEB-INF/views/response_forward_view_name.jsp";
	 }	
	
	 /*################## redirect ###########################*/
	 /*
		 * redirect view객체반환
		 */
		@RequestMapping("/response_redirect_view_object")
		public View response_redirect_view_object() {
		
			/*
			 * 1. Controller가 RedirectView 객체(URL:response_redirect_view_object.jsp)반환
			 * 2. DispatcherServlet은 반환받은 View(RedirectView)객체의 renderMergedOutputModel 메쏘드호출
			 *      
			 *      protected void renderMergedOutputModel(	Map<String, Object> model, 
			 *      										HttpServletRequest request, 
			 *      										HttpServletResponse response) throws Exception;
			 * 
			 * 3. renderMergedOutputModel 메쏘드에서는 response_redirect_view_object.jsp로 redirect
			 */
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl("response_redirect_view_object.jsp");
			return redirectView;
			
		}
		/*
		 * redirect view name(String)반환
		 */
		@RequestMapping("/response_redirect_view_name")
		public String response_redirect_view_name() {
			/* 
			  0 . Controller 가 view name(String)반환
			  1 . DispatcherServlet객체는  View객체를 얻기위해 ViewResolver객체를 찾는다.
			  2 . 현재생성된  ViewResolver객체는 InternalResourceViewResolver 객체만 존재하므로 
			  	  InternalResourceViewResolver객체에 Controller로부터 반환받은 view name을 주고 View객체를 요청한다.
			  3 . InternalResourceViewResolver 객체는 view name 이 redirect: 로시작하므로  RedirectView 객체를 생성하고 view name을 url로 설정한후반환한다.
			      (url:response_redirect_view_name.jsp)를 반환
			  4 . DispatcherServlet객체는 반환받은 RedirectView 객체의 render 메쏘드호출한다: 	
				  (response_redirect_view_name.jsp 로 redirect 가이루어진다)	
				  
			  << mcv-config-view-resolver.xml >>
			  <!-- InternalResourceViewResolver -->
			  <bean id="myInternalResourceViewResolver" 
					class="org.springfram		ework.web.servlet.view.InternalResourceViewResolver"/>	  
				  
			*/
			return "redirect:response_redirect_view_name.jsp";
		}
		
		/*#########################View객체가직접 xml(json,text)출력[XMLView]###########################*/
		/*
		 * xml출력 view object반환
		 */
		@RequestMapping("/response_xml_view_object")
		public View response_xml_view_object(Model model) {
			XMLView xmlView=new XMLView();
			/*
			 * 1. Controller가 XMLView 객체 반환
			 * 2. DispatcherServlet은 반환받은 View(XMLView)객체의 renderMergedOutputModel 메쏘드호출
			 *      
			 *      protected void renderMergedOutputModel(	Map<String, Object> model, 
			 *      										HttpServletRequest request, 
			 *      										HttpServletResponse response) throws Exception;
			 * 
			 * 3. renderMergedOutputModel() 메쏘드에서는 XML출력..
			 */
			List<String> friendList=new ArrayList<String>();
			friendList.add("김수미");
			friendList.add("김우미");
			friendList.add("김미미");
			friendList.add("김양미");
			friendList.add("김가미");
			friendList.add("김나미");
			model.addAttribute("friendList", friendList);
			return xmlView;
		}
		/*
		 * xml출력 view name반환
		 */
		@RequestMapping("/response_xml_view_name")
		public String response_xml_view_name(Model model) {
			List<String> friendList=new ArrayList<String>();
			friendList.add("김수미");
			friendList.add("김우미");
			friendList.add("김미미");
			friendList.add("김양미");
			friendList.add("김가미");
			friendList.add("김나미");
			model.addAttribute("friendList", friendList);
			/* 
			  0 . Controller 가 view name(String)을 반환
			  1 . DispatcherServlet객체는  View객체를 얻기위해 ViewResolver객체를 찾는다.
			  2 . 여러개의 ViewResolver객체중에 우선순위가높은 ViewResolver 객체를 선택한다 (BeanNameViewResolver)
			  3 . 선택된 ViewResolver(BeanNameViewResolver)객체에 Controller로부터 반환받은 view name(xmlView)을 주고 View객체를 요청한다.
			  4 . BeanNameViewResolver 객체는 view name 을 사용하여 이름과일치하는 View객체를 찾는다.
			  5 . BeanNameViewResolver View객체(XMLView)를 찾으면 DispatcherServlet에게 반환한다.
			  5 . BeanNameViewResolver View객체(XMLView)를 찾지못하면  DispatcherServlet이 다음우선순위의 ViewResolver(InternalResourceViewResolver)를 선택한다.
			  6 . DispatcherServlet객체는 반환받은 View객체(XMLView)객체의 renderMergedOutputModel() 메쏘드호출한다: 	
			  7 . XML출력 	  
				  
			  
			 	/********View 객체등록****************************
				@Component("xmlView")
				public class XMLView extends AbstractView {}
				
				/*******ViewResolver객체등록**********************
				1. WebConfig.java에 BeanNameViewResolver를 등록한다.
				
					@Bean
					public BeanNameViewResolver beanNameViewResolver() {
						BeanNameViewResolver beanNameViewResolver=new BeanNameViewResolver();
						beanNameViewResolver.setOrder(0);
						return beanNameViewResolver;
					}
				
				2.InternalResourceViewResolver 등록[빈으로 정의하지않아도 기본생성됨]
				  설정을변경할려면 WebConfig.java파일에 InternalResourceViewResolver등록 
			 	
				 	@Bean
					public InternalResourceViewResolver internalResourceViewResolver() {
						InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
						internalResourceViewResolver.setPrefix("/WEB-INF/views/");
						internalResourceViewResolver.setSuffix(".jsp");
						internalResourceViewResolver.setOrder(1);
						return internalResourceViewResolver;
					}	
				  
			*/
			
			return "XMLView";
		}
}