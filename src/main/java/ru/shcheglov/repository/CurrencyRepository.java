package ru.shcheglov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shcheglov.entity.Currency;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCharCode(String charCode);

    List<Currency> findAllByCharCodeIn(List<String> charCodes);
}
