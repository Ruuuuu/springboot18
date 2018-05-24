package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Id;

public class UserInfo {

    @Id
    private String id;

    @Column(name = "user_name")
    private String userName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
