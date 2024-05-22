package com.pillgood.resolver;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.pillgood.shop.user.dto.UserDto;
import com.pillgood.shop.user.entity.User;
import com.pillgood.shop.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

	private final UserRepository userRepository;
	private ModelMapper modelMapper;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean hasAuthUser = parameter.hasParameterAnnotation(AuthUser.class);
		boolean isUserType = Object.class.isAssignableFrom(parameter.getParameterType());
		if(!isUserType) {
			throw new IllegalArgumentException("@AuthUser는 반드시 User Type이어야 합니다.");
		}
		return hasAuthUser && isUserType;
	}

	@Override
	public UserDto resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory
			) throws Exception {
		try {
			String userId = (String)webRequest.getAttribute("userId",RequestAttributes.SCOPE_REQUEST);
			Optional<User> optional = userRepository.findById(userId);
			if(optional.isPresent()) {
				return  modelMapper.map(optional.get(), UserDto.class);
			}
		} catch(Exception e) {
			throw new Exception("Token has expired");
		}
		
		return null;
	}



}
