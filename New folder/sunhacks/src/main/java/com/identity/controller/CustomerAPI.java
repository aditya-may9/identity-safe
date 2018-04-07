package com.identity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {

    @RequestMapping("/test")
    public String test() {
        return "Customer test";
    }

}
