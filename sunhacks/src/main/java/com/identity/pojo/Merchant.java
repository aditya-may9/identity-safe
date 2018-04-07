package com.identity.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Merchant implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    String identity;
    String name;
    String address;
    String description;
    String email;
    String password;

    public Merchant(String identity, String name, String address, String description, String email, String password) {
        this.identity = identity;
        this.name = name;
        this.address = address;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
