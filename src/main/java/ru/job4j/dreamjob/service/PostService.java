package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

public class PostService {

    private final PostStore store = PostStore.instOf();

    private PostService() {
    }

    public static PostService getInstance() {
        return PostServiceHolder.INSTANCE;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void create(Post post) {
        store.create(post);
    }

    public void update(Post post) {
        store.update(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    private static final class PostServiceHolder {
        
        private static final PostService INSTANCE = new PostService();
    }
}
