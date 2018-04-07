package com.identity.repository;

import com.identity.pojo.CustomerSecrets;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerSecretsRepository extends MongoRepository<CustomerSecrets, String> {

    public List<CustomerSecrets> findByCustomerIdentity(String customerIdentity);
    public CustomerSecrets findByCustomerIdentityAndSecretType(String customerIdentity, String secretType);

    public Long deleteByCustomerIdentityAndSecretType(String customerIdentity, String secretType);

}
