package com.scm.demo.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Date birthDay;
    private String phoneNumber;
    private String address;

    @Column(length = 10000)
    private String profilePicture;
    
    @Column(length = 10000)
    private String about;
    private boolean fevorite = false;
    private String websiteLink;
    private String linkedInLink;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

}
