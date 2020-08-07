package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.entity.Currency;
import ru.shcheglov.repository.CurrencyRepository;
import ru.shcheglov.util.Converter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public void saveIfNotExists(ValuteDto valute) {
        String charCode = valute.getCharCode();
        if (exists(charCode)) {
            return;
        }
        Currency entity = Converter.toCurrencyEntity(valute);
        this.save(entity);
    }

    public void saveListIfNotExists(List<ValuteDto> valutes) {
        valutes.forEach(this::saveIfNotExists);
    }

    public List<Currency> findAllByCharCodes(List<ValuteDto> valutes) {
        List<String> charCodes = valutes.stream()
                .map(ValuteDto::getCharCode)
                .collect(Collectors.toList());
        return currencyRepository.findAllByCharCodeIn(charCodes);
    }

    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    public Currency findByCharCode(String charCode) {
        return currencyRepository.findByCharCode(charCode);
    }

    public boolean exists(String charCode) {
        Currency byCharCode = this.findByCharCode(charCode);
        return byCharCode != null;
    }

}
