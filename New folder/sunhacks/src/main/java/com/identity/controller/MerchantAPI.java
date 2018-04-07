package com.identity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantAPI {

    @RequestMapping("/test")
    public String test() {
        return "Merchant test";
    }

}
