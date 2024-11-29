package com.scm.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.demo.Entities.Contact;
import java.util.List;
import java.util.Optional;


public interface ContactRepo extends JpaRepository <Contact, Integer> {

    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhoneNumber(String phoneNumber);

    List<Contact> findByUserId(Integer userId);
}
