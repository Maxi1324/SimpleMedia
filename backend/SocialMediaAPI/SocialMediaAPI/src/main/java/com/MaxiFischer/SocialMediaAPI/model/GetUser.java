package com.MaxiFischer.SocialMediaAPI.model;

public class GetUser {
    Beitraege beitraege;
    GetUserOB user;

    public GetUser(Beitraege beitraege, GetUserOB user) {
        this.beitraege = beitraege;
        this.user = user;
    }

    public GetUser() {
    }

    public void setBeitraege(Beitraege beitraege) {
        this.beitraege = beitraege;
    }

    public void setUser(GetUserOB user) {
        this.user = user;
    }

    public Beitraege getBeitraege() {
        return beitraege;
    }

    public GetUserOB getUser() {
        return user;
    }

}
