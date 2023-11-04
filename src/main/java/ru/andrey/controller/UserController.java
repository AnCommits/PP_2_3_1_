package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.andrey.model.User;
import ru.andrey.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Удаление всех записей из таблицы и добавление 4 пользователей
    @GetMapping("/init")
    public String init() {
        userService.initTable();
        return "redirect:users";
    }

    // Отображение всех пользователей
    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

//    @GetMapping("/add_user")
//    public String showAddUser(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "add_user";
//    }

    @PostMapping("/add_user")
    public String addUser() {
        System.out.println("Method addUser()");
        return "redirect:users";
    }

//    @PostMapping("/addus")
//    public String addUser(@ModelAttribute User user, ModelMap model) {
//        System.out.println("Method addUser()");
//        return "redirect:users";
//    }
}
