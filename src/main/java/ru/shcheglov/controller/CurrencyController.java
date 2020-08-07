package ru.shcheglov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shcheglov.service.ParseService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final ParseService parseService;

    @GetMapping
    public String getXmlDaily() {
        log.info("Получен запрос на валютные котировки");
        parseService.getXmlAndSaveToDb();
        return "SUCCESS";
    }
}
