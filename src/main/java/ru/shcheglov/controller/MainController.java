package ru.shcheglov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.service.ParseService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final ParseService parseService;

    @GetMapping
    public String welcomeRoot() {
        return "Welcome to MY APP!";
    }

    @GetMapping("/admin")
    public String welcomeAdmin(Principal principal) {
        return "Welcome to ADMIN ZONE: " + principal.getName();
    }

    @GetMapping("/profile")
    public String welcomeProfile(Principal principal) {
        return "Welcome to PROFILE ZONE: " + principal.getName();
    }

    @GetMapping("/authenticated/currency")
    public String getXmlDaily(Principal principal) {
        log.info("Имя пользователя: {}", principal.getName());
        log.info("Получен запрос на валютные котировки");
        parseService.getXmlAndSaveToDb();
        return "SUCCESS";
    }
}
