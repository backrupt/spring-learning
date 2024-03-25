package com.itwill.user.dao.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *  사용자관리를 위하여 필요한 도메인클래스(VO,DTO)
 *  USERINFO 테이블의 각컬럼에해당하는 멤버를 가지고있다
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
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
	

}







