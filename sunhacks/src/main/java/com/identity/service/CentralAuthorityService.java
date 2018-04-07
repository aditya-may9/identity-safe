package com.identity.service;

import com.identity.pojo.CustomerSecrets;
import com.identity.pojo.MaskedSecrets;
import com.identity.repository.CustomerSecretsRepository;
import com.identity.repository.MaskedSecretsRepository;
import com.identity.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentralAuthorityService {

    @Autowired
    private CustomerSecretsRepository customerSecretsRepository;

    @Autowired
    private MaskedSecretsRepository maskedSecretsRepository;

    public CustomerSecrets addCustomerSecret(String identity, String secret, String secretType) {
        CustomerSecrets customerSecrets = new CustomerSecrets(identity, secret, secretType, GlobalUtils.getCurrentTimestamp());
        customerSecretsRepository.insert(customerSecrets);

        return customerSecrets;
    }

    public MaskedSecrets authorizeSecretToMerchant(String customerIdentity, String secretType, String merchantIdentity, Long minutes) {
        CustomerSecrets customerSecret = customerSecretsRepository.findByCustomerIdentityAndSecretType(customerIdentity, secretType);
        String maskedSecretString = GlobalUtils.maskSecret(customerSecret.getCustomerSecret());
        Long activeUntil = GlobalUtils.getCurrentTimestamp() + GlobalUtils.convertMinutesToSeconds(minutes);
        MaskedSecrets maskedSecret = new MaskedSecrets(customerIdentity, maskedSecretString, secretType, merchantIdentity, activeUntil);
        maskedSecretsRepository.insert(maskedSecret);

        return maskedSecret;
    }

}
