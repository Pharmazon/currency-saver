package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.shcheglov.entity.User;
import ru.shcheglov.repository.UserRepository;

@Service
@Order(2)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private boolean isExist(String login) {
        return userRepository.findByUsername(login) != null;
    }

    private void save(User user) {
        userRepository.save(user);
    }
}
