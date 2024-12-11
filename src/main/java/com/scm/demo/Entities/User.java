package com.scm.demo.Entities;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "user")
@Builder
@Getter
@Setter
public class User  implements UserDetails{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @Column(length = 1000)
    private String about;
    
    @Column(length = 1000)
    private String profilePicLink;
    private String phoneNumber;
    //info
    @Getter(value = AccessLevel.NONE)
    @Builder.Default
    private boolean enabled = true;
    private boolean emailVarified = false;
    private boolean phoneVarified = false;
    //self,Google,Github
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;
    // add more fields when needed
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
   
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();
   
    public User() {
    }
    
    public User(Integer id, String name, String email, String password, String about, String profilePicLink,
            String phoneNumber, boolean enabled, boolean emailVarified, boolean phoneVarified, Providers provider,
            String providerUserId, List<Contact> contacts, List<String> roleList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilePicLink = profilePicLink;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.emailVarified = emailVarified;
        this.phoneVarified = phoneVarified;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.contacts = contacts;
        this.roleList = roleList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // traveresed the roleList and create SimpleGrantedAuthority and return it
        Collection<SimpleGrantedAuthority> roles = roleList.stream().
                                                    map(role -> new SimpleGrantedAuthority(role)).
                                                    collect(Collectors.toList());
        return roles;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
                + ", profilePicLink=" + profilePicLink + ", phoneNumber=" + phoneNumber + ", enabled=" + enabled
                + ", emailVarified=" + emailVarified + ", phoneVarified=" + phoneVarified + ", provider=" + provider
                + ", providerUserId=" + providerUserId + ", roleList=" + roleList + "]";
    }

    

}
