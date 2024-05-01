package com.itwill.ajax.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ajax.domain.News;

import jakarta.websocket.server.PathParam;
@RestController
public class JSONMessageConverterRestController {
	/*
	 << @ResponseBody >> 
	  - ViewResolver-->View  를 사용하지않는다 
	  - MessageConverter(text,xml,json)가 변경한데이타를 
	    	HttpResponse객체가클라이언트로 응답한다. 
	  - @RestController 어노테이션을 사용하면
	    생략가능하다.
	 */
	
	@GetMapping(value = "/news",produces ="application/json;charset=UTF-8")
	public List<News> newsTitlesListJson(){
		return this.getNewsList();
	}
	@GetMapping(value = "/news/{no}")
	public News newsTitleJson(@PathVariable(name="no") int no) {
		News news=getNewsList().get(no);
		return news;
	}
	@GetMapping(value="/map/news")
	public Map<String, Object> newsTitlesMapJson() {
		int status=1;
		String msg="";
		Object data=new ArrayList<>();
		data = getNewsList();
		
		Map resultMap=new HashMap<String,Object>();
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		
		return resultMap;
		
	}
	@GetMapping(value = "/map/news/{no}")
	public Map<String,Object> newsTitleMapJson(@PathVariable(name="no") int no) {
		int status=1;
		String msg="상세보기성공";
		List data=new ArrayList<>();
		data.add(getNewsList().get(no));
		
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		resultMap.put("data", data);
		
		return resultMap ;
	}
	
	
	private List<News> getNewsList() {
		List<News> newsList = new ArrayList<News>();
		newsList.add(new News(1, "김순옥vs남궁민, 빅매치 성사…'연인'파트2, '소옆경' 이어 '7인의 탈출'도 꺾을까?", "MD포커스", new Date().toLocaleString()));
		newsList.add(new News(2, "MBC가 금토드라마 '연인' 파트2를 10월 13일 방송 편성 확정했다고 13일 공식 발표했다.", "MBC뉴스", new Date().toLocaleString()));
		newsList.add(new News(3, "BTS 정국 \"헬스장 오고 배달음식 보내는 팬 有…부적절한 말 무시도 내 선택\"", "종합", new Date().toLocaleString()));
		newsList.add(new News(4, "방탄소년단 정국이 사생 피해를 고백하며 아미와의 적절한 관계를 유지하는 법을 공개했다.", "TBC뉴스", new Date().toLocaleString()));
		newsList.add(new News(5, "폭행 피해자 3명에 사과?…김히어라 측 고소장 준비 중, 추가입장 無", "OMY뉴스", new Date().toLocaleString()));
		newsList.add(new News(6, "'현빈♥' 손예진, 2년만 공개 골프나들이 \"결혼도 하고, 아이도 낳고\" ", "조선뉴스", new Date().toLocaleString()));
		newsList.add(new News(7, "배우 손예진(본명 손언진·41)이 2년 만의 공개 골프 나들이를 예고했다.", "YTN뉴스", new Date().toLocaleString()));
		newsList.add(new News(8, "남태현·서민재 필로폰 투약 혐의 10월 19일 첫 공판", "CBS뉴스", new Date().toLocaleString()));
		newsList.add(new News(9, "'소년판타지' 제작사, '팀 이탈' 유준원에 30억 손배소", "ITWILL뉴스", new Date().toLocaleString()));
		return newsList;
	}

}
