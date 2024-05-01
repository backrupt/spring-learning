package com.itwill.security.user.entity;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude ={"user"})
public class UserAuthority implements GrantedAuthority {
	@SequenceGenerator(name = "user_authority_no_seq", sequenceName = "user_authority_no_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_authority_no_seq")
	@Id
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "userid",referencedColumnName = "userid")
	@ToString.Exclude
	@JsonIgnore
	private UserDetailsImpl user;
	
	@NonNull
	private String authority;
    
	public UserAuthority(UserDetailsImpl user,String authority){
		this.user=user;
		this.authority=authority;
	}
}
