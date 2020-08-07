package ru.shcheglov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shcheglov.entity.CurrencyQuota;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyQuotaRepository extends JpaRepository<CurrencyQuota, Long> {

    List<CurrencyQuota> findAllByDate(LocalDate date);
}
