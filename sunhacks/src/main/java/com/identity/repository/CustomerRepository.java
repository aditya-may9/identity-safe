package com.identity.repository;

import com.identity.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByEmail(String email);

    public Customer findByEmailAndPassword(String email, String password);

}