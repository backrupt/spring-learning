package com.springboot.security.service;


import com.springboot.security.data.dto.UserDto;

public interface UserService {
    UserDto join(String id, String password, String name, String role);
    UserDto login(String id, String password) throws RuntimeException;
}