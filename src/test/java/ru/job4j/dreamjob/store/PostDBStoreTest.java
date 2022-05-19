package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "name", "description", LocalDate.now(), false, new City(1, "default city"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "updatedName", "description", LocalDate.now(), false, new City(1, "default city"));
        store.update(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePostsCity() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Another Job", "smt about", LocalDate.now(), false, new City(2, "another city"));
        store.update(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getCity().getId(), is(post.getCity().getId()));
    }
}