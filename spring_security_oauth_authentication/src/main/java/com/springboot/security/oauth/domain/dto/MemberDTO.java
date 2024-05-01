package com.springboot.security.oauth.domain.dto;



import com.springboot.security.oauth.domain.entity.Member;
import com.springboot.security.oauth.util.validation.annotation.Email;
import com.springboot.security.oauth.util.validation.annotation.Nickname;
import com.springboot.security.oauth.util.validation.annotation.Password;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MemberDTO {

	@Getter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Join {
		@Nickname
		private String nickname;

		@Email
		private String email;

		// @Password
		private String password;

		/*
		public Join(String nickname, String email, String password) {
		    this.nickname = nickname;
		    this.email = email;
		    this.password = password;
		}
		*/
	}

	@Getter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class UpdatePassword {
//        @Password
		private String oldPassword;

		@Password
		private String newPassword;

	}

	@Getter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class findPassword {
		@Email
		private String email;

		@Nickname
		private String nickname;
	}

	@Getter
	@ToString
	public static class Response {
		private Long id;
		private String nickname;
		private String email;
	}

	public static Response entityToDto(Member member) {
		Response response = new Response();
		response.id = member.getId();
		response.nickname = member.getNickname();
		response.email = member.getEmail();
		return response;
	}

}
