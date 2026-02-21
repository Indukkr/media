package com.social.media.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(mappedBy = "groups")//SocialUser class is owner of this Many to many  Relationship
    private Set<SocialUser> socialUsers = new HashSet<>();
}
