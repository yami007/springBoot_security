package com.yami.springboot_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/hello")
    public String login(){
        return "word";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/roleLogin")
    public String roleLogin(){
        return "roleLogin";
    }
}
