package com.scm.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.demo.Entities.User;
import com.scm.demo.Utilities.Message;
import com.scm.demo.Utilities.MessageType;
import com.scm.demo.service.UserService;

import forms.UserForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PageController {

    private Logger log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserService userService;

    // index
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    

    //pocessing Register user
    @PostMapping("/registerUser")
    public String registerProcess( @Valid @ModelAttribute UserForm userForm,BindingResult result, HttpSession session){
        System.out.println("Register user Process...");
        //fetch the form data
        System.out.println(userForm);
        //validate data
        if(result.hasErrors()){
            return "register";
        }
        // todo
        //save to database
        User user = User.builder().
            name(userForm.getName()).
            email(userForm.getEmail()).
            password(userForm.getPassword()).
            phoneNumber(userForm.getPhoneNumber()).
            about(userForm.getAbout()).build();
            userService.saveUser(user);
        //message
        Message succMessage = Message.builder().msgContent("User Registerd Successfully!!!").type(MessageType.green).build();
        session.setAttribute("message", succMessage);
        //redirect
        return "redirect:/register"; 
    }

    //page routes
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "AB's Tech");
        model.addAttribute("userName", "Abhijeet Shinde");
        model.addAttribute("gitRepo", "https://github.com/abhijeetshindedev/SCM");
        
       return "home"; 
    }

    @RequestMapping("/test")
    public String testPage(Model model) {
        System.out.println("test page loading...");
        // model.addAttribute("name", "AB's Tech");
        // model.addAttribute("userName", "Abhijeet Shinde");
        // model.addAttribute("gitRepo", "https://github.com/abhijeetshindedev/SCM");
        Message succMessage = Message.builder().
            msgContent("User Registerd Successfully!!!").
            type(MessageType.green).
            build();
        model.addAttribute("message", succMessage);
        //redirect
        return "test"; 
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
        System.out.println("contact page loading...");
        return "contact";
    }

    // Loading page for login 
    @RequestMapping("/signin")
    public String signIn(Model model){
        // model.addAttribute("isLogin", false);
        System.out.println("Sign In page loading...");
        return "signin";
    }

    // Loading page for register
    @RequestMapping("/register")
    public String register(Model model){
        // model.addAttribute("isLogin", false);
        System.out.println("Sign up page loading...");

        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    

}   
