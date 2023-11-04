package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.andrey.model.User;
import ru.andrey.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add_user")
    public String showAddUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/addus")
    public String addUser(@ModelAttribute User user, ModelMap model) {
        System.out.println("Method addUser()");
        return "redirect:users";
    }
}
