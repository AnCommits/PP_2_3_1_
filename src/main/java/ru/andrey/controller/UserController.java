package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/init")
    public String init() {
        userService.initTable();
        return "redirect:users";
    }

    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userView", new UserView());
        return "users";
    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute UserView userView) {
        User user = userView.getUser();
        System.out.println(user);
        userService.addUser(user);
        return "redirect:users";
    }

    @GetMapping("/remove_user")
    public String removeUser(@RequestParam(name = "id") long id) {
        userService.removeUserById(id);
        return "redirect:users";
    }
}
