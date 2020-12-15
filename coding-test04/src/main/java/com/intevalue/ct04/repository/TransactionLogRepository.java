package com.intevalue.ct04.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intevalue.ct04.entity.TransactionLog;


@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long>   {
	public Optional<List<TransactionLog>> findByUserIdOrderByTransactionDateDesc(long userId);

}
