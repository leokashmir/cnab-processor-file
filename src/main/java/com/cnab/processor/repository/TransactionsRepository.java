package com.cnab.processor.repository;

import com.cnab.processor.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository @Transactional
public interface TransactionsRepository extends JpaRepository<Transaction,Long> {
}
