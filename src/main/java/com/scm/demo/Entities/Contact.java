package com.scm.demo.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Date birthDay;
    private String phoneNumber;
    private String address;

    @Column(length = 1000)
    private String profilePicture;
    
    @Column(length = 1000)
    private String about;

    @Builder.Default
    private boolean fevorite = false;
    private String websiteLink;
    private String linkedInLink;

    private String cloudinaryPublicId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();




    

}
