package com.MaxiFischer.SocialMediaAPI.model;

import com.MaxiFischer.SocialMediaAPI.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.sql.Date;

public class GetBeitrag {
    private String ueberschrift;
    private String text;
    private String img;
    private java.sql.Date erstellungsdatum;
    private Integer likes;
    private String date;
    private GetUserOB ersteller;

    public GetBeitrag(String ueberschrift, String text, String img, Date erstellungsdatum, Integer likes,  GetUserOB ersteller) {
        this.ueberschrift = ueberschrift;
        this.text = text;
        this.img = img;
        this.erstellungsdatum = erstellungsdatum;
        this.likes = likes;
        this.date = date;
        this.ersteller = ersteller;
    }

    public GetBeitrag() {
    }

    public GetBeitrag(Beitrag b, UserRepository repo) {
        this.ueberschrift = b.getUeberschrift();
        this.text = b.getText();
        this.img = b.getImg();
        this.erstellungsdatum = b.getErstellungsdatum();
        this.likes = b.getLikes();
        this.date = b.getErstellungsdatum().toString();
        this.ersteller = new GetUserOB(repo.findById(b.getUserId()).get());
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setErsteller( GetUserOB ersteller) {
        this.ersteller = ersteller;
    }

    public  GetUserOB getErsteller() {
        return ersteller;
    }
}
