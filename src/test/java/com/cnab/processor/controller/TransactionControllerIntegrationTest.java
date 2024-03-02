package com.cnab.processor.controller;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.DocFlavor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findTransactionsReturnsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/find")
                        .param("pageNumber", "1")
                        .param("pageSize", "1")
                        .header("companyName", "exampleCompany")
                        .header("companyId", "123456")
                        .header("accountOrigin", "account123")
                        .header("accountDestination", "account456")
                        .header("type", "type123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
