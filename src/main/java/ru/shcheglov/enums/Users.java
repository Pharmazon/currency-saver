package ru.shcheglov.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.shcheglov.entity.User;

@Getter
@AllArgsConstructor
public enum Users {

    USER("user", "user", "user@example.com", true),
    ADMIN("admin", "admin", "admin@example.com", true),
    SUPERADMIN("superadmin", "superadmin", "superadmin@example.com", true);

    private String login;
    private String password;
    private String email;
    private Boolean isEnabled;

    public static User getUser(Users user) {
        return new User(user.getLogin(), user.getPassword(), user.getEmail(), user.getIsEnabled());
    }
}
