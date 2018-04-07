package com.identity.controller;

import com.identity.pojo.Customer;
import com.identity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/test")
    @CrossOrigin
    public String test() {
        return "Customer test";
    }

    @RequestMapping("/registerCustomerToCentralAuthority")
    @CrossOrigin
    public Customer registerCustomerToCentralAuthority(@RequestParam(value = "identity") String identity,
                                                       @RequestParam(value = "name") String name,
                                                       @RequestParam(value = "address") String address,
                                                       @RequestParam(value = "email") String email,
                                                       @RequestParam(value = "password") String password) {
        return customerService.registerCustomerToCentralAuthority(identity, name, address, email, password);
    }

    @RequestMapping("/unregisterCustomerFromCentralRepository")
    @CrossOrigin
    public Customer unregisterCustomerFromCentralRepository(@RequestParam(value = "email") String email) {
        return customerService.unregisterCustomerFromCentralRepository(email);
    }

    @RequestMapping("/updateCustomerData")
    @CrossOrigin
    public Customer updateCustomerData(@RequestParam(value = "identity") String identity,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value = "address") String address,
                                       @RequestParam(value = "email") String email,
                                       @RequestParam(value = "password") String password) {
        return customerService.updateCustomerData(identity, name, address, email, password);
    }

    @RequestMapping("/getCustomerData")
    @CrossOrigin
    public Customer getCustomerData(@RequestParam(value = "email") String email) {
        return customerService.getCustomerData(email);
    }

}
