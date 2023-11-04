package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.andrey.model.User;
import ru.andrey.service.UserService;
import ru.andrey.util.UserView;

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
        model.addAttribute("userView", new UserView());
        return "add_user";
    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute UserView userView) {
        System.out.println("Method addUser()");
        System.out.println(userView.getFirstName());
        User user = userView.getUser();
        System.out.println(user);
        userService.addUser(user);
        return "redirect:users";
    }
}
