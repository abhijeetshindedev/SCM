package com.scm.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {


    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "AB's Tech");
        model.addAttribute("userName", "Abhijeet Shinde");
        model.addAttribute("gitRepo", "https://github.com/abhijeetshindedev/SCM");
        
       return "home"; 
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("isLogin", true);
        System.out.println("about page loading...");
        return "about";
    }

    @RequestMapping("/service")
    public String service(Model model){
        model.addAttribute("isLogin", false);
        System.out.println("service page loading...");
        return "service";
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        // model.addAttribute("isLogin", false);
        System.out.println("contac page loading...");
        return "contact";
    }

    @RequestMapping("/signin")
    public String signIn(Model model){
        // model.addAttribute("isLogin", false);
        System.out.println("contac page loading...");
        return "signin";
    }

    @RequestMapping("/register")
    public String register(Model model){
        // model.addAttribute("isLogin", false);
        System.out.println("contac page loading...");
        return "register";
    }
}   
