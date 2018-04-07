package com.identity.pojo;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class MaskedSecrets {

    @Id
    public String id;

    public String customerIdentity;
    public String maskedSecret;
    public String secretType;
    public String merchant;
    public Long activeUntil;

    public MaskedSecrets(String customerIdentity, String maskedSecret, String secretType, String merchant, Long activeUntil) {
        this.customerIdentity = customerIdentity;
        this.maskedSecret = maskedSecret;
        this.secretType = secretType;
        this.merchant = merchant;
        this.activeUntil = activeUntil;
    }

    public String getCustomerIdentity() {
        return customerIdentity;
    }

    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public String getMaskedSecret() {
        return maskedSecret;
    }

    public void setMaskedSecret(String maskedSecret) {
        this.maskedSecret = maskedSecret;
    }

    public String getSecretType() {
        return secretType;
    }

    public void setSecretType(String secretType) {
        this.secretType = secretType;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Long getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Long activeUntil) {
        this.activeUntil = activeUntil;
    }
}
