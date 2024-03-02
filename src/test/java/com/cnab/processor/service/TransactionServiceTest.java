package com.cnab.processor.service;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.repository.TransactionsRepository;
import com.cnab.processor.repository.query.specifications.TransactionsSpecifitaion;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DataJpaTest
public class TransactionServiceTest {

    @Mock
    private TransactionsRepository repository;

    @Mock
    private TransactionsSpecifitaion specifitaion;

    @Mock
    private TransactionService transactionService;

    @Test
    public void testFindTransactions() {

        Pageable pageable = mock(Pageable.class);
        pageable = PageRequest.of(1, 1);
        String[] filter = {"Enterprise", "0123456", "23132", "12313", "C"};

        when(repository.findAll(specifitaion.dinamicQuery(), pageable)).thenReturn(mock(Page.class));
        when(specifitaion.dinamicQuery()).thenReturn(any());
        List<Transaction> result = transactionService.findTransactions(pageable, filter);

        assertNotNull(result);
    }
}
