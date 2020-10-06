package com.example.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/6 20:56
 */
@Controller
public class SecurityController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/content")
    public String content() {
        return "content";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAuthority('ADMIN')")//进入方法前进行校验
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
