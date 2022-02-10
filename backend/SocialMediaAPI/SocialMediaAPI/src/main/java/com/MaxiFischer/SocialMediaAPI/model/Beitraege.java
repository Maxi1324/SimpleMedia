package com.MaxiFischer.SocialMediaAPI.model;

import com.MaxiFischer.SocialMediaAPI.Controller.BeitraegeController;
import com.MaxiFischer.SocialMediaAPI.jpa.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Beitraege {

    ArrayList<GetBeitrag> beitraege;

    public Beitraege(ArrayList<GetBeitrag> beitrag) {
        this.beitraege = beitrag;
    }

    public Beitraege(ArrayList<Beitrag> beitrag, UserRepository repo) {
        beitraege = new ArrayList<>();
        beitrag.forEach((Beitrag b) -> {
            GetBeitrag beitrag3 = new GetBeitrag(b, repo);
            BeitraegeController.useBeitragImage(beitrag3);
            beitraege.add(beitrag3);
        });
    }

    public void setBeitraege(ArrayList<GetBeitrag> beitraege) {
        this.beitraege = beitraege;
    }

    public ArrayList<GetBeitrag> getBeitraege() {
        return beitraege;
    }

    @Override
    public String toString() {
        return "Beitraege{" +
                "beitraege=" + beitraege +
                '}';
    }
}
