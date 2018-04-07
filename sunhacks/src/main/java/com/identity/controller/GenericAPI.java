package com.identity.controller;

import com.identity.pojo.Customer;
import com.identity.pojo.Merchant;
import com.identity.scheduler.ScheduledTasks;
import com.identity.service.CustomerService;
import com.identity.service.MerchantService;
import com.identity.utils.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generic")
public class GenericAPI {

    @Autowired
    CustomerService customerService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    ScheduledTasks scheduledTasks;

    @RequestMapping("/test")
    @CrossOrigin
    public String test() {
        return "Generic test";
    }

    @RequestMapping("/login")
    @CrossOrigin
    public Boolean login(@RequestParam(value = "email") String email,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "userType") String userType) {
        if (userType.equals(GlobalConstants.CUSTOMER)) {
            Customer customer = customerService.getCustomerForEmailAndPassword(email, password);
            if (customer == null) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            Merchant merchant = merchantService.getMerchantForEmailAndPassword(email, password);
            if (merchant == null) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }
    }

    @RequestMapping("/clearExpiredMaskedSecrets")
    @CrossOrigin
    public String clearExpiredMaskedSecrets() {
        scheduledTasks.removeExpiredMaskedSecrets();
        return "DONE";
    }

}
