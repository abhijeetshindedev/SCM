package com.scm.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.demo.Entities.Contact;
import com.scm.demo.Entities.User;
import com.scm.demo.exception.ResourceNotFoundException;
import com.scm.demo.repo.ContactRepo;
import com.scm.demo.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{

    Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact saveContact(Contact contact) {
        Contact savedContact = contactRepo.save(contact);
        return savedContact;
    }

    @Override
    public Contact getById(Integer id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
        return contact;
    }

    @Override
    public Contact getContactByEmail(String email) {
        Contact contact = contactRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Contact", "email", email));
        return contact;
    }

    @Override
    public Contact updateContact(Contact Contact) {
        // get contact tobe update
        Contact contact = contactRepo.findById(Contact.getId()).orElseThrow(() -> new ResourceNotFoundException("Contact", "id", Contact.getId()));
        contact.setName(Contact.getName());
        contact.setEmail(Contact.getEmail());
        contact.setPhoneNumber(Contact.getPhoneNumber());
        contact.setAddress(Contact.getAddress());
        contact.setAbout(Contact.getAbout());
        contact.setFevorite(Contact.isFevorite());
        contact.setWebsiteLink(Contact.getWebsiteLink());
        contact.setLinkedInLink(Contact.getLinkedInLink());
        contact.setProfilePicture(Contact.getProfilePicture());
        Contact updatedContact = contactRepo.save(contact);
        return updatedContact;
    }

    @Override
    public void deleteContact(Integer id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
        contactRepo.delete(contact);
    }

    @Override
    public Boolean contactExists(Integer id) {
        Contact contact = contactRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
        if (contact != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean contactExists(String email) {
        Contact contact = contactRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Contact", "email", email));
        if (contact != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Contact> getAllContact() {
        List<Contact> contacts = contactRepo.findAll();
        return contacts;
    }

    @Override
    public List<Contact> getContactsByUser(User user) {
        List<Contact> contacts = contactRepo.findByUser(user);
        return contacts;
    }

}
