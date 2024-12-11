package com.scm.demo.Controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.demo.Entities.Contact;
import com.scm.demo.Entities.User;
import com.scm.demo.Utilities.Message;
import com.scm.demo.Utilities.MessageType;
import com.scm.demo.config.OAuthHelper;
import com.scm.demo.service.ContactService;
import com.scm.demo.service.ImageService;
import com.scm.demo.service.UserService;

import forms.ContactForm;
import forms.UserForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/add")
    public String addContactView( Model model){ 
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }

    @PostMapping("/registerContact")
    public String registerProcess( @Valid @ModelAttribute ContactForm contactForm,BindingResult result, 
            HttpSession session, Principal principal){
        log.info("Register Contact Process...");
        //fetch the form data
        log.info("ContactForm : " + contactForm); 
        //validate data
        if(result.hasErrors()){
            // session.setAttribute("message", Message.builder().msgContent("Invalid Data").type(MessageType.red).build());
            log.info("erors : " + result.getAllErrors());
            return "user/add_contact";
        }
        String email = OAuthHelper.getEmailFromPrincipal(principal);
        log.info("email : " + email);
        // get logged in user
        User loggedUser = userService.getUserByEmail(email);

        log.info("loggedIn user : " + loggedUser.getName());

        // Process Profile Picture
        log.info("picture details : " + contactForm.getContactPicture().getOriginalFilename());

        // create unique file name
        String fileName = UUID.randomUUID().toString();
        log.info("File_Name : "+fileName);
        // upload image
        String fileURL = imageService.uploadFile(contactForm.getContactPicture(), fileName);
        log.info("fileURL : " + fileURL);
        //save to database
        Contact contact = Contact.builder().
            name(contactForm.getName()).
            email(contactForm.getEmail()).
            phoneNumber(contactForm.getPhoneNumber()).
            address(contactForm.getAddress()).
            about(contactForm.getAbout()).
            websiteLink(contactForm.getWebsiteLink()).
            linkedInLink(contactForm.getLinkedInLink()).
            profilePicture(fileURL).
            fevorite(contactForm.getFevorite()).
            birthDay(contactForm.getBirthDay()).
            cloudinaryPublicId(fileName).
            build();
        contact.setUser(loggedUser);
        contactService.saveContact(contact);
        //message
        Message succMessage = Message.builder().msgContent("Contact added Successfully!!!").type(MessageType.green).build();
        session.setAttribute("message", succMessage);
        //redirect
        return "redirect:/user/contacts/add"; 
    }

    @RequestMapping()
    public String viewContacts( Principal principal , Model model){
        String userEmail = OAuthHelper.getEmailFromPrincipal(principal);
        User user = userService.getUserByEmail(userEmail);
        log.info("user : " + user);
        List<Contact> contacts = contactService.getContactsByUser(user);
        model.addAttribute("contacts",contacts);
        return "user/contacts";
    }

    
}
