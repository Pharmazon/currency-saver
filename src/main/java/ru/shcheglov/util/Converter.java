package ru.shcheglov.util;

import lombok.experimental.UtilityClass;
import ru.shcheglov.entity.Currency;
import ru.shcheglov.dto.ValuteDto;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class Converter {

    public Currency toEntity(ValuteDto valute) {
        Currency currency = new Currency();
        currency.setCharCode(valute.getCharCode());
        currency.setCode(valute.getId());
        currency.setName(valute.getName());
        currency.setNominal(valute.getNominal());
        currency.setValue(valute.getValue());
        currency.setNumCode(valute.getNumCode());
        return currency;
    }

    public List<Currency> toEntity(List<ValuteDto> list) {
        return list.stream()
                .map(Converter::toEntity)
                .collect(Collectors.toList());
    }
}
