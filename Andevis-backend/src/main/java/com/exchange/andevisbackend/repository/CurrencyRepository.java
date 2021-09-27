package com.exchange.andevisbackend.repository;

import com.exchange.andevisbackend.common.CurrencyCode;
import com.exchange.andevisbackend.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.SortedSet;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    SortedSet<Currency> findAllByRateDate (LocalDate date);

    @Query(name = "SELECT c FROM Currency c WHERE c.currencyCode = :code AND c.rateDate = :date")
    Currency findByCurrencyCodeAndRateDate (CurrencyCode code, LocalDate date);
}
