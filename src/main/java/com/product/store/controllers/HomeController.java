package com.product.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String Hello(Model model){
        model.addAttribute("name","Leodoss");
        return "index";
    }
}
