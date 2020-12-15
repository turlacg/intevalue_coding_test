package com.intevalue.ct03.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intevalue.ct03.entity.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	
	public Optional<ExchangeRate> findDistinctByToCurrency(String toCurrency);

}
