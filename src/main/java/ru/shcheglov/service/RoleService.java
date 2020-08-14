package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.shcheglov.entity.Role;
import ru.shcheglov.enums.Roles;
import ru.shcheglov.repository.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Set;

@Service
@Order(1)
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        Roles.getAll().stream()
                .filter(roleName -> !isExist(roleName))
                .forEach(roleName -> save(new Role(roleName)));
    }

    private boolean isExist(String roleName) {
        return roleRepository.findByName(roleName) != null;
    }

    private void save(Role role) {
        roleRepository.save(role);
    }

    Set<Role> getRolesByNames(Set<String> roleNames) {
        return roleRepository.findAllByNameIn(new ArrayList<>(roleNames));
    }
}
