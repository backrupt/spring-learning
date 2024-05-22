package com.pillgood.shop.user.kakao;

import java.net.URI;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KaKaoService {
	@Value("${api.kakao.rest_api_key}")
	private String kakaoRestApiKey;
	@Value("${api.kakao.javascript_key}")
	private String kakaoJavascriptApiKey;
	@Value("${api.kakao.redirect_url}")
	private String redirect_url;
	
	public JSONObject getToken(String code) {
		
		String kakaoAuthUrl = "https://kauth.kakao.com";
		String reqUrl = "/oauth/token";
		String accessToken = "";
		
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create(kakaoAuthUrl+reqUrl);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", "application/json");
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		//MultiValueMap과 그냥 Map의 차이점은 그냥 Map은 하나의 key에 하나의 value가 할당되어서 
		//중복된 key값을 put 했을때 최종적으로 마지막에 put한 키벨류값만 Map에 저장되지만 
		//multiValueMap은 put은 아니지만 add를 하는 경우에 key값이 중복되면 해당 key값에 list로 모두 들어간다. 
		parameters.set("grant_type", "authorization_code");
		parameters.set("client_id", kakaoRestApiKey);
		parameters.set("redirect_uri", redirect_url);
		parameters.set("code", code);
		//카카오의 문서의 양식에 맞는 형태와 값으로 세팅한뒤 요청
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<MultiValueMap<String,Object>>(parameters,headers);
		//http 요청
		ResponseEntity<JSONObject> apiResponse = restTemplate.postForEntity(uri, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		
		return responseBody;
	}
	
	public KakaoProfile getKakaoProfile(String accessToken) throws Exception {
		String kakaoApiUrl = "https://kapi.kakao.com";
		
		RestTemplate restTemplate = new RestTemplate();
		String reqUrl = "/v2/user/me";
		URI uri = URI.create(kakaoApiUrl + reqUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer " + accessToken);
		//카카오 사용자 정보 가져오기 헤더에 없던데 아래는 왜 추가해주는거지?
		headers.set("KakaoAK", kakaoRestApiKey);
		
		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
		//RestTemplate를 사용하여 POST 요청을 수행하고 응답 객체를 kakaoResponseEntity에 저장 응답 데이터는 문자열 형식으로 받도록 설정
		ResponseEntity<String> kakaoResponseEntity = restTemplate.postForEntity(uri, entity, String.class);
		
		String kakaoProfileStr= kakaoResponseEntity.getBody();
		System.out.println(">>>"+kakaoProfileStr);
		
		ObjectMapper objectMapper = new ObjectMapper();
		//JSON 문자열을 Java 객체(KakaoProfile)로 변환
		KakaoProfile kakaoProfile = objectMapper.readValue(kakaoProfileStr, KakaoProfile.class);
		
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		return kakaoProfile;
	}
}
