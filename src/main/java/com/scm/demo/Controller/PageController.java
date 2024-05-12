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
}
