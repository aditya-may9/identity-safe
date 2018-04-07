package com.identity.repository;

import com.identity.pojo.MaskedSecrets;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaskedSecretsRepository extends MongoRepository<MaskedSecrets, String> {

    public List<MaskedSecrets> findByCustomerIdentity(String customerIdentity);
    public List<MaskedSecrets> findByActiveUntilLessThan(Long activeUntil);

}