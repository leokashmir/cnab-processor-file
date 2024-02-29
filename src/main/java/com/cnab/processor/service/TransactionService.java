package com.cnab.processor.service;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.repository.TransactionsRepository;
import com.cnab.processor.repository.query.specifications.TransactionsSpecifitaion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository repository;
    private TransactionsSpecifitaion specifitaion;
    public List<Transaction> findTransactions(Pageable page, String... filter) {;

        Transaction transaction =
                Transaction.builder()
                        .company(Company.of(null,filter[0], filter[1] ))
                        .accountOrigin(filter[2])
                        .accountDestination(filter[3])
                        .type(filter[4])
                .build();

        specifitaion = new TransactionsSpecifitaion(transaction);


        return repository.findAll(specifitaion.dinamicQuery(),page).getContent();
    }

}
