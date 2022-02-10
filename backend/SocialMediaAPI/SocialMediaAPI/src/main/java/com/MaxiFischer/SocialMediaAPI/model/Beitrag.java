package com.MaxiFischer.SocialMediaAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "b_beitrag")
public class Beitrag {

    @Id
    @Column(name = "b_id")
    private Integer id;
    @Column(name = "b_u_id")
    private Integer userId;
    @Column(name = "b_ueberschrift")
    private String ueberschrift;
    @Column(name = "b_text")
    private String text;
    @Column(name = "b_img")
    private String img;
    @Column(name = "b_erstellungsdatum")
    private java.sql.Date erstellungsdatum;
    @Column(name = "b_likes")
    private Integer likes;

    public Beitrag(Integer id, Integer userId, String ueberschrift, String text, String img, Date erstellungsdatum, Integer likes) {
        this.id = id;
        this.userId = userId;
        this.ueberschrift = ueberschrift;
        this.text = text;
        this.img = img;
        this.erstellungsdatum = erstellungsdatum;
        this.likes = likes;
    }

    public Beitrag() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUeberschrift() {
        return ueberschrift;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    public Date getErstellungsdatum() {
        return erstellungsdatum;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setErstellungsdatum(Date erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
