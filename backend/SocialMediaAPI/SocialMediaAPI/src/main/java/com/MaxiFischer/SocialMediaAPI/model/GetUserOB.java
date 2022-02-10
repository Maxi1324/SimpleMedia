package com.MaxiFischer.SocialMediaAPI.model;

import com.MaxiFischer.SocialMediaAPI.Controller.BeitraegeController;

import java.sql.Date;

public class GetUserOB {
    private Integer id;
    private String nutzername;
    private String mail;
    private Integer geschlecht;
    private String profilBild;
    private Integer rolle;
    private Date erstanmeldung;
    private Date letztanmeldung;
    private Boolean aktiv;

    public GetUserOB(Integer id, String nutzername, String mail, Integer geschlecht, String profilBild, Integer rolle, Date erstanmeldung, Date letztanmeldung, Boolean aktiv) {
        this.id = id;
        this.nutzername = nutzername;
        this.mail = mail;
        this.geschlecht = geschlecht;
        this.profilBild = profilBild;
        this.rolle = rolle;
        this.erstanmeldung = erstanmeldung;
        this.letztanmeldung = letztanmeldung;
        this.aktiv = aktiv;
    }

    public GetUserOB(User user){
        this.id = user.getId();
        this.nutzername = user.getNutzername();
        this.mail = user.getMail();
        this.geschlecht = user.getGeschlecht();
        this.profilBild = user.getProfilBild();
        this.rolle = user.getRolle();
        this.erstanmeldung = user.getErstanmeldung();
        this.letztanmeldung = user.getLetztanmeldung();
        this.aktiv = user.getAktiv();
        BeitraegeController.useImageUser(this);
    }

    public GetUserOB() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGeschlecht(Integer geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setProfilBild(String profilBild) {
        this.profilBild = profilBild;
    }

    public void setRolle(Integer rolle) {
        this.rolle = rolle;
    }

    public void setErstanmeldung(Date erstanmeldung) {
        this.erstanmeldung = erstanmeldung;
    }

    public void setLetztanmeldung(Date letztanmeldung) {
        this.letztanmeldung = letztanmeldung;
    }

    public void setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
    }

    public Integer getId() {
        return id;
    }

    public String getNutzername() {
        return nutzername;
    }

    public String getMail() {
        return mail;
    }

    public Integer getGeschlecht() {
        return geschlecht;
    }

    public String getProfilBild() {
        return profilBild;
    }

    public Integer getRolle() {
        return rolle;
    }

    public Date getErstanmeldung() {
        return erstanmeldung;
    }

    public Date getLetztanmeldung() {
        return letztanmeldung;
    }

    public Boolean getAktiv() {
        return aktiv;
    }

    @Override
    public String toString() {
        return "GetUserOB{" +
                "id=" + id +
                ", nutzername='" + nutzername + '\'' +
                ", mail='" + mail + '\'' +
                ", geschlecht=" + geschlecht +
                ", profilBild='" + profilBild + '\'' +
                ", rolle=" + rolle +
                ", erstanmeldung=" + erstanmeldung +
                ", letztanmeldung=" + letztanmeldung +
                ", aktiv=" + aktiv +
                '}';
    }
}
