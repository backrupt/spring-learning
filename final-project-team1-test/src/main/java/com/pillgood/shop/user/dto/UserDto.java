package com.pillgood.shop.user.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDto {
	private String id;
	private String password;
	private String name;
	private Date birthday;
	private String gender;
	private String phone;
	
}
