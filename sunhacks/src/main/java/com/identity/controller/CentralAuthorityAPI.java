package com.identity.controller;

import com.identity.pojo.CustomerSecrets;
import com.identity.pojo.MaskedSecrets;
import com.identity.service.CentralAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/centralAuthority")
public class CentralAuthorityAPI {

    @Autowired
    CentralAuthorityService centralAuthorityService;

    @RequestMapping("/test")
    public String test() {
        return "CA test";
    }

    @RequestMapping("/addCustomerSecret")
    public Boolean addCustomerSecret(@RequestParam(value = "customerIdentity") String customerIdentity,
                                     @RequestParam(value = "secret") String secret,
                                     @RequestParam(value = "secretType") String secretType) {
        CustomerSecrets customerSecret = centralAuthorityService.addCustomerSecret(customerIdentity, secret, secretType);
        if (customerSecret != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @RequestMapping("/authorizeSecretToMerchant")
    public MaskedSecrets addCustomerSecret(@RequestParam(value = "customerIdentity") String customerIdentity,
                                           @RequestParam(value = "secretType") String secretType,
                                           @RequestParam(value = "merchantIdentity") String merchantIdentity,
                                           @RequestParam(value = "minutes") Long minutes) {
        return centralAuthorityService.authorizeSecretToMerchant(customerIdentity, secretType, merchantIdentity, minutes);
    }

    @RequestMapping("/checkIfMaskedSecretIsValid")
    public Boolean checkIfMaskedSecretIsValid(@RequestParam(value = "maskedSecret") String maskedSecret,
                                              @RequestParam(value = "merchantIdentity") String merchantIdentity,
                                              @RequestParam(value = "customerIdentity") String customerIdentity,
                                              @RequestParam(value = "secretType") String secretType) {
        return centralAuthorityService.checkIfMaskedSecretIsValid(maskedSecret, merchantIdentity, customerIdentity, secretType);
    }
}
