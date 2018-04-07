package com.identity.repository;

import com.identity.pojo.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface MerchantRepository extends MongoRepository<Merchant, String> {

    public Merchant findByEmail(String email);

    public Merchant findByEmailAndPassword(String email, String password);

    public List<Merchant> findByIdentity(String identity);

}