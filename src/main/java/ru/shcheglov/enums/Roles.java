package ru.shcheglov.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum Roles {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_SUPERUSER("ROLE_SUPERADMIN");

    private String name;

    public static Set<String> getRole(Roles role) {
        Set<String> roles = new HashSet<>();
        switch (role) {
            case ROLE_USER:
                roles.add(Roles.ROLE_USER.getName());
                break;
            case ROLE_ADMIN:
                roles.add(Roles.ROLE_USER.getName());
                roles.add(Roles.ROLE_ADMIN.getName());
                break;
            case ROLE_SUPERUSER:
                roles.add(Roles.ROLE_USER.getName());
                roles.add(Roles.ROLE_ADMIN.getName());
                roles.add(Roles.ROLE_SUPERUSER.getName());
                break;
        }

        return roles;
    }

    public static Set<String> getAll() {
        Set<String> roles = new HashSet<>();
        for (Roles value : Roles.values()) {
            roles.add(value.getName());
        }
        return roles;
    }
}
