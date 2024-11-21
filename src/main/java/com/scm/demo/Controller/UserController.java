package com.scm.demo.Controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.demo.Entities.User;
import com.scm.demo.config.OAuthHelper;
import com.scm.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;



    //user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal){
        System.out.println("User Dashboard Loading......");
        return "user/dashboard";
    }

    // user profile picture
    @GetMapping("/profile")
    public String userProfile(){
        System.out.println("User Profile Loading.......");
        return "user/profile";
    }

    // user add contact

    // user view contact

    // user edit contact

    // user delete contact

    // user search contact

}
