package ru.shcheglov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.service.ParseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final ParseService parseService;

    @GetMapping
    public String getXmlDaily() {
        parseService.getXmlAndSaveToDb();
        return "SUCCESS";
    }
}
