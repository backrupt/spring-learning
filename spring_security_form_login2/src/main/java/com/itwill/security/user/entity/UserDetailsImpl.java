package com.itwill.security.user.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserDetailsImpl implements UserDetails {
	
	@SequenceGenerator(name = "users_no_seq", sequenceName = "users_no_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_no_seq")
	@Id
	private Long no;
	@Column(unique = true, nullable = false)
	private String userid;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private Set<UserAuthority> authorities = new HashSet<>();
	
	private boolean enabled;
	@Override
	public String getUsername() {
		return userid;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

}
