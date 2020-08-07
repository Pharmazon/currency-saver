package ru.shcheglov.util;

import lombok.experimental.UtilityClass;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.entity.Currency;

@UtilityClass
public class Converter {

    public Currency toCurrencyEntity(ValuteDto valute) {
        Currency currency = new Currency();
        currency.setCharCode(valute.getCharCode());
        currency.setCode(valute.getId());
        currency.setName(valute.getName());
        currency.setNominal(valute.getNominal());
        currency.setNumCode(valute.getNumCode());
        return currency;
    }

}
