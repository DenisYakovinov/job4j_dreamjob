package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public Collection<User> findAll() {
        List<User> posts = store.findAll();
        return posts;
    }

    public Optional<User> create(User user) {
        return store.add(user);
    }

    public void update(User user) {
        store.update(user);
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    public Optional<User> findUserByEmailAndPwd(String email, String pass) {
        return store.findUserByEmailAndPwd(email, pass);
    }
}
