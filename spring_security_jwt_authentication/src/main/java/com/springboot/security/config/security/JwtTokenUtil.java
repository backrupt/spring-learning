package com.springboot.security.config.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
	JWT(Json Web Token)란 Json 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 Web Token이다. 
	JWT는 토큰 자체를 정보로 사용하는 Self-Contained 방식으로 정보를 안전하게 전달한다. 
 */
public class JwtTokenUtil {

	// 토큰 생성
	public String createToken(String jwtSecretKey) {
		/*
		 * 	HEADER 부분 설정
		 * 
			헤더는 typ과 alg 두 가지 정보로 구성된다. 
				alg는 헤더(Header)를 암호화 하는 것이 아니고, 
				Signature를 해싱하기 위한 알고리즘을 지정하는 것이다.
			typ: 토큰의 타입을 지정 ex) JWT
			alg: 알고리즘 방식을 지정하며, 서명(Signature) 및 토큰 검증에 사용 ex) HS256(SHA256) 또는 RSA
		*/
		Map<String, Object> headers = new HashMap<>();

		// Type 설정
		headers.put("typ", "JWT");

		// HS256는 해시 알고리즘의 일종으로, base64와 같이 임의로 디코딩을 할 수 없다.
		headers.put("alg", "HS256");

		/*
		 * PAYLOAD 부분 설정
		 * 
			토큰의 페이로드에는 토큰에서 사용할 정보의 조각들인 클레임(Claim)이 담겨 있다. 
			클레임은 총 3가지로 나누어지며, 
			Json(Key/Value) 형태로 다수의 정보를 넣을 수 있다.
			header와 payload에는 기밀정보는 넣으면 안된다.
		 */
		Map<String, Object> payloads = new HashMap<>();

		// API 용도에 맞게 properties로 관리하여 사용하는것을 권장한다.
		payloads.put("apiKey", "******");
		// 토큰 유효 시간 (2시간)
		Long expiredTime = 1000 * 60L * 60L * 2L;

		Date ext = new Date(); // 토큰 만료 시간
		ext.setTime(ext.getTime() + expiredTime);

		/*
		 * 	토큰 Builder
		 * 
			등록된 클레임은 토큰 정보를 표현하기 위해 이미 정해진 종류의 데이터들로, 모두 선택적으로 작성이 가능하며 사용할 것을 권장한다. 
			또한 JWT를 간결하게 하기 위해 key는 모두 길이 3의 String이다. 
			여기서 subject로는 unique한 값을 사용하는데, 사용자 이메일을 주로 사용한다.
			
			iss: 토큰 발급자(issuer)
			sub: 토큰 제목(subject)
			aud: 토큰 대상자(audience)
			exp: 토큰 만료 시간(expiration), NumericDate 형식으로 되어 있어야 함 ex) 1480849147370
			nbf: 토큰 활성 날짜(not before), 이 날이 지나기 전의 토큰은 활성화되지 않음
			iat: 토큰 발급 시간(issued at), 토큰 발급 이후의 경과 시간을 알 수 있음
			jti: JWT 토큰 식별자(JWT ID), 중복 방지를 위해 사용하며, 일회용 토큰(Access Token) 등에 사용
			
		 *
		 * Signature(서명)
		 * 
			서명(Signature)은 토큰을 인코딩하거나 유효성 검증을 할 때 사용하는 고유한 암호화 코드이다. 
			서명(Signature)은 위에서 만든 헤더(Header)와 페이로드(Payload)의 값을 각각 BASE64로 인코딩하고,
			인코딩한 값을 비밀 키를 이용해 헤더(Header)에서 정의한 알고리즘으로 해싱을 하고, 이 값을 다시 BASE64로 인코딩하여 생성한다.
		 */
		String jwt = Jwts.builder().setHeader(headers) // Headers 설정
				.setClaims(payloads) // Claims 설정
				.setSubject("user-auth") // 토큰 용도
				.setExpiration(ext) // 토큰 만료 시간 설정
				.signWith(SignatureAlgorithm.HS256, jwtSecretKey.getBytes()) // HS256과 Key로 Sign
				.compact(); // 토큰 생성

		return jwt;
	}

	// 토큰 검증
	public Map<String, Object> verifyJWT(String jwtSecretKey, String authorization)
			throws UnsupportedEncodingException {

		Map<String, Object> claimMap = null;

		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecretKey.getBytes("UTF-8")) // Set SignKey
					.parseClaimsJws(authorization) // 파싱 및 검증, 실패 시 에러
					.getBody();

			claimMap = claims;

			// Date expiration = claims.get("exp", Date.class);
			// String data = claims.get("data", String.class);
		} catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
			System.out.println("# EXPIR TOKEN ===");
			System.out.println(e);
		} catch (Exception e) { // 그외 에러났을 경우
			System.out.println("# ERROR TOKEN ===");
			System.out.println(e);
		}

		return claimMap;
	}
}
