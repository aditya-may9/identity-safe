package com.identity.controller;

import com.identity.pojo.Merchant;
import com.identity.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantAPI {

    @Autowired
    MerchantService merchantService;

    @RequestMapping("/test")
    @CrossOrigin
    public String test() {
        return "Merchant test";
    }

    @RequestMapping("/registerMerchantToCentralAuthority")
    @CrossOrigin
    public Merchant registerMerchantToCentralAuthority(@RequestParam(value = "identity") String identity,
                                                       @RequestParam(value = "name") String name,
                                                       @RequestParam(value = "address") String address,
                                                       @RequestParam(value = "email") String email,
                                                       @RequestParam(value = "password") String password,
                                                       @RequestParam(value = "description") String description) {
        return merchantService.registerMerchantToCentralAuthority(identity, name, address, email, password, description);
    }

    @RequestMapping("/unregisterMerchantFromCentralRepository")
    @CrossOrigin
    public Merchant unregisterMerchantFromCentralRepository(@RequestParam(value = "email") String email) {
        return merchantService.unregisterMerchantFromCentralRepository(email);
    }

    @RequestMapping("/updateMerchantData")
    @CrossOrigin
    public Merchant updateMerchantData(@RequestParam(value = "identity") String identity,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value = "address") String address,
                                       @RequestParam(value = "email") String email,
                                       @RequestParam(value = "password") String password,
                                       @RequestParam(value = "description") String description) {
        return merchantService.updateMerchantData(identity, name, address, email, password, description);
    }

    @RequestMapping("/getMerchantData")
    @CrossOrigin
    public Merchant getMerchantData(@RequestParam(value = "email") String email) {
        return merchantService.getMerchantData(email);
    }

    @RequestMapping("/checkIfMerchantInfoIsLegit")
    @CrossOrigin
    public Boolean checkIfMerchantInfoIsLegit(@RequestParam(value = "merchant1Identity") String merchant1Identity,
                                              @RequestParam(value = "merchant2Identity") String merchant2Identity,
                                              @RequestParam(value = "maskedSecret") String maskedSecret,
                                              @RequestParam(value = "secretType") String secretType,
                                              @RequestParam(value = "customerIdentity") String customerIdentity) {
        return merchantService.checkIfMerchantInfoIsLegit(merchant1Identity, merchant2Identity, maskedSecret, secretType, customerIdentity);
    }

}
