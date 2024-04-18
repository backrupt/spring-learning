package com.itwill.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 *  사용자관리를 위하여 필요한 도메인클래스(VO,DTO)
 *  USERINFO 테이블의 각컬럼에해당하는 멤버를 가지고있다
 */
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class User {
	/*
	 * =================================== 
	 * 이름 		널? 		유형
	 * =================================== 
	 * USERID 	NOT NULL VARCHAR2(100) 
	 * PASSWORD	NOT NULL VARCHAR2(100) 
	 * NAME 	NOT NULL VARCHAR2(200) 
	 * EMAIL 			 VARCHAR2(300)
	 * ===================================
	 */
	private String userId;
	private String password;
	private String name;
	private String email;

	
	/*
	 *패쓰워드 일치여부 검사 
	 */
	public boolean isMatchPassword(String password){
		boolean isMatch=false;
		if(this.password.equals(password)){
			isMatch=true;
		}
		return isMatch;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]\n";
	}

}







