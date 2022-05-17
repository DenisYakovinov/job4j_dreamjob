package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.util.SessionUtil;

import javax.servlet.http.HttpSession;

@Controller
@ThreadSafe
public class IndexController {
    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = SessionUtil.getUserFromSession(session);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}
