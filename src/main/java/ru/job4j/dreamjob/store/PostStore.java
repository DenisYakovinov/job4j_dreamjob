package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class PostStore {

    private final AtomicInteger idCounter = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Job", "java dev", LocalDate.now()));
        posts.put(2, new Post(2, "Middle Job", "java dev", LocalDate.now()));
        posts.put(3, new Post(3, "Senior Job", "java dev", LocalDate.now()));
        idCounter.addAndGet(posts.size());
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void create(Post post) {
        post.setId(idCounter.incrementAndGet());
        posts.putIfAbsent(post.getId(), post);
    }

    /*update with the same id*/
    public void update(Post post) {
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}

