package com.MaxiFischer.SocialMediaAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "l_liked")
public class Liked {
    @Id
    @Column(name = "l_id")
    private Integer id;
    @Column(name = "l_u_id")
    private Integer userid;
    @Column(name = "l_b_id")
    private Integer beitragId;
}
