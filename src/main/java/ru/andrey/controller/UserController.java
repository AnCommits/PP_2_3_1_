package ru.andrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.andrey.dto.UserDto;
import ru.andrey.mapper.UserMapper;
import ru.andrey.model.User;
import ru.andrey.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private String sortedByColumn = "id";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUsers(@RequestParam(required = false, name = "column") Optional<String> column, ModelMap model) {
        sortedByColumn = column.orElse(sortedByColumn);
        List<User> users = userService.getAllUsersSorted(sortedByColumn);
        model.addAttribute("users", users);
        model.addAttribute("userDto", new UserDto());
        return "users";
    }

    @GetMapping("/add_user")
    public String addUser(@ModelAttribute UserDto userDto) {
        User user = UserMapper.toUser(userDto);
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
        UserDto userDto = UserMapper.toUserDto(userService.getUserById(id));
        model.addAttribute("userDto", userDto);
        return "show_update_user";
    }

    @GetMapping("/update_user")
    public String updateUser(@ModelAttribute UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        userService.updateUser(user);
        return "redirect:users";
    }
}
