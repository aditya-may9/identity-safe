package com.identity.repository;

import com.identity.pojo.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MerchantRepository extends MongoRepository<Merchant, String> {

    public Merchant findByEmail(String email);

    public Merchant findByEmailAndPassword(String email, String password);

}