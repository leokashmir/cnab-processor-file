package com.cnab.processor.service;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.repository.TransactionsRepository;
import com.cnab.processor.repository.query.specifications.TransactionsSpecifitaion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@DataJpaTest
public class TransactionServiceTest {
    @Mock
    private TransactionsRepository repository;

    @Mock
    private TransactionsSpecifitaion specifitaion;

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private Page<Transaction> page;

    private  Transaction transaction;

    @BeforeEach
    public void setUp() {

        Transaction transaction = Transaction.builder()
                .company(Company.of(1L, "Enterprise", "0123456"))
                .type("C")
                .value(new BigDecimal(12345)).build();
        specifitaion = new TransactionsSpecifitaion(transaction);
    }
      //TODO - Corrigir este teste
//    @Test
//    public void testFindTransactions() {
//
//        String[] filter = {"Enterprise", "0123456", "23132", "12313", "C"};
//
//
//        List<Transaction> listT = new ArrayList<>();
//        listT.add(transaction);
//        page = new PageImpl<Transaction>(listT);
//
//        when(repository.findAll(specifitaion.dinamicQuery(), mock(Pageable.class))).thenReturn(page);
//
//        //when(specifitaion.dinamicQuery()).thenReturn(any());
//
//        List<Transaction> result = transactionService.findTransactions(mock(Pageable.class), filter);
//
//        assertNull(result);
//    }
}
