package com.cnab.processor.controller;

import com.cnab.processor.model.Transaction;
import com.cnab.processor.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionService service;

    @GetMapping("/find")
    public ResponseEntity<List<Transaction>> findTransacions
            (@RequestHeader(value = "companyName", required = false) String companyName,
             @RequestHeader(value = "companyId", required = false) String companyId,
             @RequestHeader(value = "accountOrigin", required = false) String accountOrigin,
             @RequestHeader(value = "accountDestination", required = false) String accountDestination,
             @RequestHeader(value = "type", required = false) String type,
             @RequestParam(value  = "pageNumber", defaultValue = "0") int pageNumber,
             @RequestParam(value  = "pageNumber", defaultValue = "20") int pageSize) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<List<Transaction>>(
                service.findTransactions( page, companyName, companyId, accountOrigin, accountDestination, type), HttpStatus.OK);


    }
}
