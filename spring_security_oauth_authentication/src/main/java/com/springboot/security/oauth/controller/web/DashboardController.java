package com.springboot.security.oauth.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardController {
    @GetMapping("/dashboard/myInfo")
    public String myInfo(){
        return "dashboard/myInfo";
    }

    
}
