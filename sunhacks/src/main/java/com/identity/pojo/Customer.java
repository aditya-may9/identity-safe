package com.identity.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    String identity;
    String name;
    String address;
    String email;
    String password;

    public Customer(String identity, String name, String address, String email, String password) {
        this.identity = identity;
        this.name = name;
        this.address = address;
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
