package com.example.MyFirstSpringWebApp.controller;

import com.example.MyFirstSpringWebApp.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    int id;
    static List<User> users = new ArrayList<>();

    public void userDelete(int id){
        users.remove(id);
    }

    @GetMapping("/one")
    public String getUser(Model model) {
        return "user";
    }

    @PostMapping("/createExample")
    public void postCreateExample(HttpServletRequest request, HttpServletResponse response) throws IOException {
        id++;
        users.add(new User(id, "Example"));
        response.sendRedirect("http://localhost:8080/user/list");
    }

    @PostMapping("/create")
    public void postMapping(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = request.getParameter("name");
        id++;
        users.add(new User(id, str));
        response.sendRedirect("http://localhost:8080/user/list");
    }

    @GetMapping("/list")
    public String getUsers(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("users", users);
        return "userList";
    }

//    @GetMapping("/{userId}")
//    public String getOneUser(@PathVariable("userId") int id) throws IOException {
//        return "userList";
//    }
    @GetMapping("/delete")
    public String delete(Model model, HttpServletRequest request) {
        UserController user = new UserController();
        user.userDelete(Integer.parseInt(request.getParameter("text"))-1);
        return "delete";
    }
}
