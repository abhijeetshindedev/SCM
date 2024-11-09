package com.scm.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard
    @GetMapping("/dashboard")
    public String userDashboard(){
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
