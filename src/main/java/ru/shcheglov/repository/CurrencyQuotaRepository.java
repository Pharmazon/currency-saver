package ru.shcheglov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shcheglov.entity.CurrencyQuota;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyQuotaRepository extends JpaRepository<CurrencyQuota, Long> {

    List<CurrencyQuota> findAllByDate(LocalDate date);
}
