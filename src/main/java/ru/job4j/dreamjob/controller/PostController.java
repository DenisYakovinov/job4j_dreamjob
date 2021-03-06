package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@ThreadSafe
public class PostController {

    private final PostService postService;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        User user = SessionUtil.getUserFromSession(session);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        User user = SessionUtil.getUserFromSession(session);
        model.addAttribute("user", user);
        model.addAttribute("post", new Post(0, "Fill the field", "fill", LocalDate.now(), false));
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post, @RequestParam("city.id") int cityId) {
        post.setCreated(LocalDate.now());
        post.setCity(cityService.findById(cityId));
        postService.create(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id, HttpSession session) {
        User user = SessionUtil.getUserFromSession(session);
        model.addAttribute("user", user);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post, @RequestParam("city.id") int cityId) {
        post.setCity(cityService.findById(cityId));
        postService.update(post);
        return "redirect:/posts";
    }
}
