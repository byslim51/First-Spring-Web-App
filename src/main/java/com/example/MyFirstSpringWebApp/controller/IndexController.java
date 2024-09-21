package com.example.MyFirstSpringWebApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request) {
        String str = request.getParameter("text");
        String age = request.getParameter("age");
        model.addAttribute("text", str);
        model.addAttribute("age", age);
        return "index";
    }
}
