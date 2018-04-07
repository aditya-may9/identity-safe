package com.identity.repository;

import com.identity.pojo.CustomerSecrets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerSecretsRepository extends MongoRepository<CustomerSecrets, String> {

    public List<CustomerSecrets> findByCustomerIdentity(String customerIdentity);
    public CustomerSecrets findByCustomerIdentityAndSecretType(String customerIdentity, String secretType);

}
