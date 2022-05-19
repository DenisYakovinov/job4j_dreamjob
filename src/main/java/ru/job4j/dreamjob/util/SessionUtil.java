package ru.job4j.dreamjob.util;

import ru.job4j.dreamjob.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    private SessionUtil() {
    }

    public static User getUserFromSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Guest");
        }
        return user;
    }
}
