package com.MaxiFischer.SocialMediaAPI.model;

public class AuthRequest {

    private String username;
    private String passwort;

    public AuthRequest(String username, String passwort) {
        this.username = username;
        this.passwort = passwort;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
