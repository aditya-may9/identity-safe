package com.identity.pojo;

public class User {

    private String userName;
    private String password;
    private String type;

    public User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public User() {

    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
