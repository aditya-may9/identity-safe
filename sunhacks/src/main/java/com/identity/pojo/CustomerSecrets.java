package com.identity.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class CustomerSecrets implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    public String id;

    public String customerIdentity;
    public String customerSecret;
    public String secretType;
    public Long createdTime;

    public String getCustomerIdentity() {
        return customerIdentity;
    }

    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public String getCustomerSecret() {
        return customerSecret;
    }

    public void setCustomerSecret(String customerSecret) {
        this.customerSecret = customerSecret;
    }

    public String getSecretType() {
        return secretType;
    }

    public void setSecretType(String secretType) {
        this.secretType = secretType;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public CustomerSecrets(String customerIdentity, String customerSecret, String secretType, Long createdTime) {
        this.customerIdentity = customerIdentity;
        this.customerSecret = customerSecret;
        this.secretType = secretType;
        this.createdTime = createdTime;
    }
}
