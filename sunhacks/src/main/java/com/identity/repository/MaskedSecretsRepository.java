package com.identity.repository;

import com.identity.pojo.MaskedSecrets;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaskedSecretsRepository extends MongoRepository<MaskedSecrets, String> {

    public List<MaskedSecrets> findByActiveUntilLessThan(Long activeUntil);

    public List<MaskedSecrets> findByCustomerIdentityAndMaskedSecretAndSecretTypeAndMerchant(String customerIdentity, String maskedSecret, String secretType, String merchant);

    public List<MaskedSecrets> findByCustomerIdentityAndSecretTypeAndMerchant(String customerIdentity, String secretType, String merchant);

    public List<MaskedSecrets> findByMaskedSecretAndSecretTypeAndMerchant(String maskedSecret, String secretType, String merchant);

    public List<MaskedSecrets> deleteByCustomerIdentityAndSecretType(String customerIdentity, String secretType);

    public MaskedSecrets findByMerchantAndSecretTypeAndCustomerIdentity(String merchant, String secretType, String customerIdentity);

    public Long deleteByCustomerIdentityAndSecretTypeAndMerchant(String customerIdentity, String secretType, String merchant);
}