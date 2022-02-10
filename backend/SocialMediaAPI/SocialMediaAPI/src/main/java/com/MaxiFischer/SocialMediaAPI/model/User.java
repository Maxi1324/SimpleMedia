package com.MaxiFischer.SocialMediaAPI.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;

@Entity
@Table(name = "u_user")
public class User {

    @Id
    @Column(name = "u_id")
    private Integer id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "u_nutzername")
    private String nutzername;
    @Column(name = "u_passwort")
    private String passwort;
    @Column(name = "u_mail")
    private String mail;
    @Column(name = "u_g_geschlecht")
    private Integer geschlecht;
    @Column(name = "u_profilbild")
    private String profilBild;
    @Column(name = "u_r_rolle")
    private Integer rolle;
    @Column(name = "u_erstanmeldung")
    private Date erstanmeldung;
    @Column(name = "u_letztanmeldung")
    private Date letztanmeldung;
    @Column(name = "u_aktiv")
    private Boolean aktiv;

    public User(String nutzername, String passwort, String mail, Integer geschlecht, Integer rolle, Date erstanmeldung, Date letztanmeldung,String img) {
        this.nutzername = nutzername;
        this.passwort = passwort;
        this.mail = mail;
        this.geschlecht = geschlecht;
        this.rolle = rolle;
        this.erstanmeldung = erstanmeldung;
        this.letztanmeldung = letztanmeldung;
        this.profilBild = img;
    }

    public User() {

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

    public Integer getGeschlecht() {
        return geschlecht;
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

    public void setNutzername(String nutzername) {
        this.nutzername = nutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGeschlecht(Integer geschlecht) {
        this.geschlecht = geschlecht;
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

    public org.springframework.security.core.userdetails.User toUserDetails() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("User"));
        return new org.springframework.security.core.userdetails.User(nutzername, passwort, auth);
    }

    public Boolean getAktiv() {
        return aktiv;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nutzername='" + nutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                ", mail='" + mail + '\'' +
                ", geschlecht=" + geschlecht +
                ", rolle=" + rolle +
                ", erstanmeldung=" + erstanmeldung +
                ", letztanmeldung=" + letztanmeldung +
                ", aktiv=" + aktiv +
                '}';
    }

    public String getProfilBild() {
        return profilBild;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProfilBild(String profilBild) {
        this.profilBild = profilBild;
    }

    public void setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
    }
}
