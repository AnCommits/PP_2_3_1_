package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.andrey.model.User;
import ru.andrey.service.UserService;
import ru.andrey.util.InitTable;
import ru.andrey.util.UserView;

import java.util.List;

@Controller
public class UserController {

    private long id;

    private final UserService userService;
    private final InitTable initTable;

    public UserController(UserService userService, InitTable initTable) {
        this.userService = userService;
        this.initTable = initTable;
    }

    @GetMapping("/init")
    public String init() {
        initTable.initTable();
        return "redirect:users";
    }

    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userView", new UserView());
        return "users";
    }

    @GetMapping("/add_user")
    public String addUser(@ModelAttribute UserView userView) {
        User user = userView.getUser();
        userService.addUser(user);
        return "redirect:users";
    }

    @GetMapping("/remove_user")
    public String removeUser(@RequestParam(name = "id") long id) {
        userService.removeUserById(id);
        return "redirect:users";
    }

    @GetMapping("/show_update_user")
    public String show_update_user(@RequestParam(name = "id") long id, ModelMap model) {
        this.id = id;
        UserView userView = UserView.getUserView(userService.getUserById(id));
        model.addAttribute("userView", userView);
        return "show_update_user";
    }

    @GetMapping("/update_user")
    public String updateUser(@ModelAttribute UserView userView) {
        User user = userView.getUser();
        System.out.println(userView.getFirstName());
        user.setId(this.id);
        userService.updateUser(user);
        return "redirect:users";
    }
}
