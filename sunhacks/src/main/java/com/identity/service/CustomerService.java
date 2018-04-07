package com.identity.service;

import com.identity.pojo.Customer;
import com.identity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CentralAuthorityService centralAuthorityService;

    public Customer registerCustomerToCentralAuthority(String identity, String name, String address, String email, String password) {
        return centralAuthorityService.registerCustomerToCentralAuthority(identity, name, address, email, password);
    }

    public Customer unregisterCustomerFromCentralRepository(String email) {
        return centralAuthorityService.unregisterCustomerFromCentralRepository(email);
    }

    public Customer updateCustomerData(String identity, String name, String address, String email, String password) {
        return centralAuthorityService.updateCustomerData(identity, name, address, email, password);
    }

    public Customer getCustomerData(String email) {
        return centralAuthorityService.getCustomerData(email);
    }

    public Customer getCustomerForEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
}
