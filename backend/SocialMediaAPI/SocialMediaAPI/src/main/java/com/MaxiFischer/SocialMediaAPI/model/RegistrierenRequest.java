package com.MaxiFischer.SocialMediaAPI.model;

public class RegistrierenRequest {
    private String nutzername;
    private String passwort;
    private String mail;
    private int geschlecht;
    private String img;

    public RegistrierenRequest(String nutzername, String passwort, String mail, int geschlecht, String img) {
        this.nutzername = nutzername;
        this.passwort = passwort;
        this.mail = mail;
        this.geschlecht = geschlecht;
        this.img = img;
    }

    public RegistrierenRequest() {
    }

    public String getImg() {
        return img;
    }

    public String getNutzername() {
        return nutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getMail() {
        return mail;
    }

    public int getGeschlecht() {
        return geschlecht;
    }

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGeschlecht(int geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
