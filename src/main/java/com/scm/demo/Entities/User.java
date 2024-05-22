package com.scm.demo.Entities;

import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @Column(length = 10000)
    private String about;
    
    @Column(length = 10000)
    private String profilePicLink;
    private String phoneNumber;
    //info
    private boolean enabled = false;
    private boolean emailVarified = false;
    private boolean phoneVarified = false;
    //self,Google,Github
    @Enumerated
    private Providers provider = Providers.self;
    private String providerUserId;
    // add more fields when needed
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();



}
