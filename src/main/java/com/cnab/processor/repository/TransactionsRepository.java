package com.cnab.processor.repository;

import com.cnab.processor.model.Transaction;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.query.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface TransactionsRepository extends  JpaSpecificationExecutor<Transaction>, JpaRepository<Transaction,Long> {




}
