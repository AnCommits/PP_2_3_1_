package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.andrey.service.UserService;

@Controller
public class InitController {

    private final UserService userService;

    public InitController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/init")
    public String init() {
        userService.initTable();
        return "redirect:users";
    }
}
