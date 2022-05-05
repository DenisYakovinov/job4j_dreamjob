package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {
    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Job", "java dev", LocalDate.now()));
        posts.put(2, new Post(2, "Middle Job", "java dev", LocalDate.now()));
        posts.put(3, new Post(3, "Senior Job", "java dev", LocalDate.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void create(Post post) {
        post.setId(posts.size() + 1);
        posts.putIfAbsent(post.getId(), post);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}

