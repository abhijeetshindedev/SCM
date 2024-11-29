package com.scm.demo.service;

import java.util.List;

import com.scm.demo.Entities.Contact;

public interface ContactService {
    Contact saveContact(Contact contact);

    Contact getById(Integer id);

    Contact getContactByEmail(String email);

    Contact updateContact(Contact Contact);

    void deleteContact(Integer id);

    Boolean contactExists(Integer id);

    Boolean contactExists(String email);

    List<Contact> getAllContact();

    List<Contact> getContactsByUser(Integer userId);
}
