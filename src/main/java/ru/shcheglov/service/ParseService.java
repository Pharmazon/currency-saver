package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shcheglov.dto.ValcursDto;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.feign.CurrencyFeignClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParseService {

    private final CurrencyFeignClient currencyFeignClient;
    private final CurrencyQuotaService quotaService;
    private final CurrencyService currencyService;

    //TODO Раскомментировать!
//    @PostConstruct
    public void getXmlAndSaveToDb() {
        log.debug("Делаем запрос на сайт cbr");
        ValcursDto dto = currencyFeignClient.getXmlDaily();
        List<ValuteDto> valutes = dto.getValutes();
        currencyService.saveListIfNotExists(valutes);
        quotaService.save(dto);
        log.info("Котировки успешно сохранены в базу");
    }
}
