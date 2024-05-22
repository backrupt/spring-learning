package com.pillgood.shop.user.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ReponseEntity -> HTTP/1.1 200 OK\r\n\r\nContent-Type: application/json\r\n\r\n"xa"

@Data
@AllArgsConstructor //이건 왜 안붙이면 오류나지?
@NoArgsConstructor
public class BaseResponse<T> {	//일반화

    private HttpStatus status;
    private String message;
    private T data;
    
    
    public static<T> BaseResponse<T> of(HttpStatus status, String message, T data) {
    	return new BaseResponse(status, message, data);
    }
    
    public static<T>  BaseResponse<T> ok(T data) {
    	return new BaseResponse(HttpStatus.OK, null,data);
    }
    
    public static<T>  BaseResponse<T> ok(T data, String message) {
    	return new BaseResponse(HttpStatus.OK, message,data);
    }
    
    public static<T>  BaseResponse<T> badRequest(String message) {
    	return new BaseResponse(HttpStatus.BAD_REQUEST, message,null);
    }
    
}