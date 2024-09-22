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

    private void recalculateIds() {
        // Переназначаем ID существующих пользователей
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setId(i + 1);
        }
    }

    @GetMapping("/one")
    public String getUser(Model model) {
        return "user";
    }

    @PostMapping("/createExample")
    public void postCreateExample(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (users.isEmpty()) {
            id = 1;
        } else {
            id = users.size() + 1;
        }
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
        recalculateIds();
        return "userList";
    }

//    @GetMapping("/{userId}")
//    public String getOneUser(@PathVariable("userId") int id) throws IOException {
//        return "userList";
//    }
    @GetMapping("/delete")
    public String delete(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserController user = new UserController();
        user.userDelete(Integer.parseInt(request.getParameter("text"))-1);
        response.sendRedirect("http://localhost:8080/user/list");
        return "delete";
    }
}
