package ru.shcheglov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shcheglov.dto.ValcursDto;
import ru.shcheglov.dto.ValuteDto;
import ru.shcheglov.entity.Currency;
import ru.shcheglov.entity.CurrencyQuota;
import ru.shcheglov.repository.CurrencyQuotaRepository;
import ru.shcheglov.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyQuotaService {

    private final CurrencyQuotaRepository quotaRepository;
    private final CurrencyService currencyService;

    void save(ValcursDto dto) {
        LocalDate date = DateTimeUtil.parseDate(dto.getDate());
        List<ValuteDto> valutes = dto.getValutes();
        valutes.forEach(valute -> saveIfNotExists(date, valute));
    }

    private List<CurrencyQuota> findAllByDate(LocalDate date) {
        return quotaRepository.findAllByDate(date);
    }

    private boolean exists(LocalDate date, String charCode) {
        List<CurrencyQuota> quotas = this.findAllByDate(date);
        Optional<CurrencyQuota> first = quotas.stream()
                .filter(quota -> quota.getCurrency().getCharCode().equals(charCode))
                .findFirst();
        return first.isPresent();
    }

    private void saveIfNotExists(LocalDate date, ValuteDto dto) {
        if (exists(date, dto.getCharCode())) {
            return;
        }
        Currency currency = currencyService.findByCharCode(dto.getCharCode());
        CurrencyQuota quota = new CurrencyQuota(date, dto.getValue());
        quota.setCurrency(currency);
        quotaRepository.save(quota);
    }
}
