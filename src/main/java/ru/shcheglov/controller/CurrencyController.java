package ru.shcheglov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public String getXmlDaily() {
        currencyService.getXmlAndSaveToDb();
        return "SUCCESS";
    }
}
