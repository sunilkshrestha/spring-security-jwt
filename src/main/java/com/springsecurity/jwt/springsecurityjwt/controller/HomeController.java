package com.springsecurity.jwt.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String sayHello(){
        return "hello from spring security";
    }
}
