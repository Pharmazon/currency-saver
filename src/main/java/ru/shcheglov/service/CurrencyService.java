package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.entity.Currency;
import ru.shcheglov.feign.CurrencyFeignClient;
import ru.shcheglov.dto.ValkursDto;
import ru.shcheglov.repository.CurrencyRepository;
import ru.shcheglov.util.Converter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyFeignClient currencyFeignClient;
    private final CurrencyRepository currencyRepository;

    public void getXmlAndSaveToDb() {
        ValkursDto dto = currencyFeignClient.getXmlDaily();
        List<ValuteDto> valutes = dto.getValutes();
        List<Currency> currencies = Converter.toEntity(valutes);
        saveAll(currencies);
    }

    private void save(Currency currency) {
        currencyRepository.save(currency);
    }

    private void saveAll(List<Currency> list) {
        list.forEach(this::save);
    }
}
