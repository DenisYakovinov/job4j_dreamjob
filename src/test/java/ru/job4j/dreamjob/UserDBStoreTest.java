package ru.job4j.dreamjob;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDBStoreTest {

    @Test
    public void whenCreateUser() {
        UserDBStore store = new UserDBStore(new Main().loadPool());
        User user = new User(1, "email@mail.com", "description");
        store.add(user);
        User userInDB = store.findById(user.getId());
        assertThat(userInDB.getEmail(), is(user.getEmail()));
    }

    @Test
    public void whenUserAlreadyExistsInDb() {
        UserDBStore store = new UserDBStore(new Main().loadPool());
        User user = new User(2, "email@mail.com", "description");
        Optional<User> userOptional = store.add(user);
        assertTrue(userOptional.isEmpty());
    }
}
