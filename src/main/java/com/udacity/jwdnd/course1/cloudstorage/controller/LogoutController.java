package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @PostMapping
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }

}
