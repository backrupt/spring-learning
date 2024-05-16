package com.itwill.user.controller;

import java.util.HashMap;

import lombok.Data;

@Data
public class Response {

    private int status;
    private String message;
    private Object data;

    public Response() {
        this.status = 0;
        this.message = "";
        this.data =new HashMap<>();
    }
    
}