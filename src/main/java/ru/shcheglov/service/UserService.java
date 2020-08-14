package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shcheglov.entity.Role;
import ru.shcheglov.entity.User;
import ru.shcheglov.enums.Roles;
import ru.shcheglov.enums.Users;
import ru.shcheglov.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Order(2)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        User user = Users.getUser(Users.USER);
        Set<Role> userRoles = roleService.getRolesByNames(Roles.getRole(Roles.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User admin = Users.getUser(Users.ADMIN);
        Set<Role> adminRoles = roleService.getRolesByNames(Roles.getRole(Roles.ROLE_ADMIN));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRoles(adminRoles);

        User superAdmin = Users.getUser(Users.SUPERADMIN);
        Set<Role> superAdminRoles = roleService.getRolesByNames(Roles.getRole(Roles.ROLE_SUPERUSER));
        superAdmin.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
        superAdmin.setRoles(superAdminRoles);

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(admin);
        list.add(superAdmin);
        list.stream()
                .filter(entry -> !isExist(entry.getLogin()))
                .forEach(this::save);
    }

    private boolean isExist(String login) {
        return userRepository.findByLogin(login) != null;
    }

    private void save(User user) {
        userRepository.save(user);
    }
}
