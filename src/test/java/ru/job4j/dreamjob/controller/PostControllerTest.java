package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class PostControllerTest {

    @Test
    void whenPostsThenReturnPageString() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post", "description", LocalDate.now(), false),
                new Post(2, "New post", "description", LocalDate.now(), false)
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, session);
        verify(session).getAttribute("user");
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    void whenAddPostThenReturnAddPostString() {
        List<City> cities = Arrays.asList(
                new City(1, "DefaultCity"),
                new City(2, "imaginaryCity"));
        User user = new User(1, "name", "email", "pass");
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(cityService.getAllCities()).thenReturn(cities);
        when(session.getAttribute("user")).thenReturn(user);
        PostController postController = new PostController(postService, cityService);
        String addPost = postController.addPost(model, session);
        verify(session).getAttribute("user");
        verify(model).addAttribute("user", user);
        verify(model).addAttribute(eq("post"), any(Post.class));
        verify(model).addAttribute("cities", cities);
        verify(model, times(3)).addAttribute(anyString(), any());
        assertThat(addPost, is("addPost"));
    }

    @Test
    void whenCreatePostThenReturnRedirectPostsString() {
        City city = new City(1, "DefaultCity");
        Post post = mock(Post.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(cityService.findById(1)).thenReturn(city);
        PostController postController = new PostController(postService, cityService);
        String redirectPosts = postController.createPost(post, 1);
        verify(post).setCreated(any(LocalDate.class));
        verify(post).setCity(city);
        verify(postService).create(post);
        assertThat(redirectPosts, is("redirect:/posts"));
    }

    @Test
    void whenFormUpdatePostThenReturnUpdatePostString() {
        User user = new User(1, "name", "email", "pass");
        Post post = new Post(1, "name", "description", LocalDate.now(), false);
        List<City> cities = Arrays.asList(
                new City(1, "DefaultCity"),
                new City(2, "imaginaryCity"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostController postController = new PostController(postService, cityService);
        when(session.getAttribute("user")).thenReturn(user);
        when(postService.findById(1)).thenReturn(post);
        when(cityService.getAllCities()).thenReturn(cities);
        String updatePost = postController.formUpdatePost(model, 1, session);
        InOrder inOrderModel = Mockito.inOrder(model);
        verify(session).getAttribute("user");
        inOrderModel.verify(model).addAttribute("user", user);
        inOrderModel.verify(model).addAttribute("post", post);
        inOrderModel.verify(model).addAttribute("cities", cities);
        assertThat(updatePost, is("updatePost"));
    }

    @Test
    void whenUpdatePostThenReturnRedirectPostsString() {
        Post post = new Post();
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String redirectPosts = postController.updatePost(post, 1);
        verify(postService).update(post);
        assertThat(redirectPosts, is("redirect:/posts"));
    }
}
