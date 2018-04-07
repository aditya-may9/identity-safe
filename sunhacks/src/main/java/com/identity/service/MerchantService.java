package com.identity.service;

import com.identity.pojo.Merchant;
import com.identity.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    CentralAuthorityService centralAuthorityService;

    public Merchant registerMerchantToCentralAuthority(String identity, String name, String address, String email, String password, String description) {
        return centralAuthorityService.registerMerchantToCentralAuthority(identity, name, address, email, password, description);
    }

    public Merchant unregisterMerchantFromCentralRepository(String email) {
        return centralAuthorityService.unregisterMerchantFromCentralRepository(email);
    }

    public Merchant updateMerchantData(String identity, String name, String address, String email, String password, String description) {
        return centralAuthorityService.updateMerchantData(identity, name, address, email, password, description);
    }

    public Merchant getMerchantData(String email) {
        return centralAuthorityService.getMerchantData(email);
    }

}
