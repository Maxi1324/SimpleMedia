package com.MaxiFischer.SocialMediaAPI.model;

public class ActivateRequest {

    private int key;
    private String userName;

    public ActivateRequest() {
    }

    public ActivateRequest(int key, String userName) {
        this.key = key;
        this.userName = userName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getKey() {
        return key;
    }

    public String getUserName() {
        return userName;
    }


}
