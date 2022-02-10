package com.MaxiFischer.SocialMediaAPI.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public class NewBeitragRequest {

    @Column(name = "b_ueberschrift")
    private String ueberschrift;
    @Column(name = "b_text")
    private String text;
    @Column(name = "b_img")
    private String img;

    public NewBeitragRequest(String ueberschrift, String text, String img) {
        this.ueberschrift = ueberschrift;
        this.text = text;
        this.img = img;

    }

    public NewBeitragRequest() {
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

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
