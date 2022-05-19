package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;

import java.util.Optional;

@Controller
@ThreadSafe
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        Optional<User> regUser = userService.create(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message", "the email is already registered");
            return "redirect:/registerFail";
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
