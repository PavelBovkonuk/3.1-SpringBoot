package ru.javamentor.springmvc.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.springmvc.model.User;
import ru.javamentor.springmvc.service.UserService;


import java.util.List;



@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String show(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "usersTable";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        User user = new User();
        user.setAge(0);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}