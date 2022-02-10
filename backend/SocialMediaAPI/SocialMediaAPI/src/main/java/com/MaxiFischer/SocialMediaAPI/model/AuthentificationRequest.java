package com.MaxiFischer.SocialMediaAPI.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "a_authentificationrequest")
public class AuthentificationRequest {
    @Id
    @Column(name = "a_id")
    private Integer id;
    @Column(name = "a_key")
    private Integer key;
    @Column(name = "a_u_id")
    private Integer userID;

    public AuthentificationRequest(Integer id, Integer key, Integer userID) {
        this.id = id;
        this.key = key;
        this.userID = userID;
    }

    public AuthentificationRequest() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getUserID() {
        return userID;
    }
}
