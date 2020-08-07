package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.entity.Currency;
import ru.shcheglov.repository.CurrencyRepository;
import ru.shcheglov.util.Converter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    private void saveIfNotExists(ValuteDto valute) {
        String charCode = valute.getCharCode();
        if (exists(charCode)) {
            return;
        }
        Currency entity = Converter.toCurrencyEntity(valute);
        this.save(entity);
    }

    void saveListIfNotExists(List<ValuteDto> valutes) {
        valutes.forEach(this::saveIfNotExists);
    }

    private void save(Currency currency) {
        currencyRepository.save(currency);
    }

    Currency findByCharCode(String charCode) {
        return currencyRepository.findByCharCode(charCode);
    }

    private boolean exists(String charCode) {
        Currency byCharCode = this.findByCharCode(charCode);
        return byCharCode != null;
    }

}
