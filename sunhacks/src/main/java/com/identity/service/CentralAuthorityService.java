package com.identity.service;

import com.identity.pojo.Customer;
import com.identity.pojo.CustomerSecrets;
import com.identity.pojo.MaskedSecrets;
import com.identity.pojo.Merchant;
import com.identity.repository.CustomerRepository;
import com.identity.repository.CustomerSecretsRepository;
import com.identity.repository.MaskedSecretsRepository;
import com.identity.repository.MerchantRepository;
import com.identity.utils.GlobalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentralAuthorityService {

    @Autowired
    private CustomerSecretsRepository customerSecretsRepository;

    @Autowired
    private MaskedSecretsRepository maskedSecretsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public CustomerSecrets addCustomerSecret(String identity, String secret, String secretType) {
        CustomerSecrets customerSecrets = new CustomerSecrets(identity, secret, secretType, GlobalUtils.getCurrentTimestamp());
        customerSecretsRepository.insert(customerSecrets);

        return customerSecrets;
    }

    public Boolean hasMerchantRegistered(String merchantIdentity) {
        if (merchantRepository.findByIdentity(merchantIdentity).size() == 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public MaskedSecrets authorizeSecretToMerchant(String customerIdentity, String secretType, String merchantIdentity, Long minutes) {
        if (!hasMerchantRegistered(merchantIdentity)) {
            return null;
        }

        CustomerSecrets customerSecret = customerSecretsRepository.findByCustomerIdentityAndSecretType(customerIdentity, secretType);
        if (customerSecret == null) {
            return null;
        }
        String maskedSecretString = GlobalUtils.maskSecret(customerSecret.getCustomerSecret());
        Long activeUntil = GlobalUtils.getCurrentTimestamp() + GlobalUtils.convertMinutesToSeconds(minutes);

        if (maskedSecretsRepository.findByCustomerIdentityAndSecretTypeAndMerchant(customerIdentity, secretType, merchantIdentity).size() != 0) {
            return null;
        }
        MaskedSecrets maskedSecret = new MaskedSecrets(customerIdentity, maskedSecretString, secretType, merchantIdentity, activeUntil);
        maskedSecretsRepository.insert(maskedSecret);

        return maskedSecret;
    }

    public Boolean checkIfMaskedSecretIsValid(String maskedSecretString, String merchantIdentity, String customerIdentity, String secretType) {
        List<MaskedSecrets> maskedSecret = maskedSecretsRepository.findByCustomerIdentityAndMaskedSecretAndSecretTypeAndMerchant(customerIdentity, maskedSecretString, secretType, merchantIdentity);
        if (maskedSecret.size() == 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Customer getCustomerData(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer registerCustomerToCentralAuthority(String identity, String name, String address, String email, String password) {
        Customer customer = new Customer(identity, name, address, email, password);
        customerRepository.save(customer);

        return customer;
    }

    public Customer updateCustomerData(String identity, String name, String address, String email, String password) {
        Customer customer = getCustomerData(email);
        if (customer == null) {
            customer = new Customer(identity, name, address, email, password);
        } else {
            customer.setAddress(address);
            customer.setIdentity(identity);
            customer.setName(name);
            customer.setPassword(password);
        }

        customerRepository.save(customer);
        return customer;
    }

    public Customer unregisterCustomerFromCentralRepository(String email) {
        Customer customer = getCustomerData(email);
        if (customer != null) {
            customerRepository.delete(customer);
        }

        return customer;
    }

    public Merchant getMerchantData(String email) {
        return merchantRepository.findByEmail(email);
    }

    public Merchant registerMerchantToCentralAuthority(String identity, String name, String address, String email, String password, String description) {
        Merchant merchant = new Merchant(identity, name, address, description, email, password);
        merchantRepository.save(merchant);

        return merchant;
    }

    public Merchant updateMerchantData(String identity, String name, String address, String email, String password, String description) {
        Merchant merchant = getMerchantData(email);
        if (merchant == null) {
            merchant = new Merchant(identity, name, address, description, email, password);
        } else {
            merchant.setAddress(address);
            merchant.setIdentity(identity);
            merchant.setName(name);
            merchant.setPassword(password);
            merchant.setDescription(description);
        }

        merchantRepository.save(merchant);
        return merchant;
    }

    public Merchant unregisterMerchantFromCentralRepository(String email) {
        Merchant merchant = getMerchantData(email);
        if (merchant != null) {
            merchantRepository.delete(merchant);
        }

        return merchant;
    }

    public Boolean unauthorizeSecretToMerchant(String customerIdentity, String secretType, String merchantIdentity) {
        Long numRecs = maskedSecretsRepository.deleteByCustomerIdentityAndSecretTypeAndMerchant(customerIdentity, secretType, merchantIdentity);

        if (numRecs == 0L) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean removeCustomerSecret(String customerIdentity, String secretType) {
        List<MaskedSecrets> maskedSecrets = maskedSecretsRepository.deleteByCustomerIdentityAndSecretType(customerIdentity, secretType);
        Long numDeleted = customerSecretsRepository.deleteByCustomerIdentityAndSecretType(customerIdentity, secretType);

        if (numDeleted == 0L) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public Boolean checkIfMerchantInfoIsLegit(String requestor, String requestee, String requestorMaskedSecret, String secretType, String customerIdentity) {
        if (!checkIfMaskedSecretIsValid(requestorMaskedSecret, requestor, customerIdentity, secretType)) {
            return Boolean.FALSE;
        }
        if (!checkIfMerchantHasMaskedSecret(requestee, secretType, customerIdentity)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public Boolean checkIfMerchantHasMaskedSecret(String merchantIdentity, String secretType, String customerIdentity) {
        List<MaskedSecrets> maskedSecret = maskedSecretsRepository.findByCustomerIdentityAndSecretTypeAndMerchant(customerIdentity, secretType, merchantIdentity);
        if (maskedSecret.size() == 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public String getMerchantMaskedSecret(String merchantIdentity, String secretType, String customerIdentity) {
        MaskedSecrets maskedSecret = maskedSecretsRepository.findByMerchantAndSecretTypeAndCustomerIdentity(merchantIdentity, secretType, customerIdentity);
        if (maskedSecret == null) {
            return null;
        } else {
            return maskedSecret.getMaskedSecret();
        }
    }

    public Boolean checkIfSecretExists(String customerIdentity, String secretType) {
        CustomerSecrets customerSecret = customerSecretsRepository.findByCustomerIdentityAndSecretType(customerIdentity, secretType);
        if (customerSecret == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public List<MaskedSecrets> getAllMaskedDataForMerchant(String merchantIdentity) {
        return maskedSecretsRepository.findByMerchant(merchantIdentity);
    }

}
