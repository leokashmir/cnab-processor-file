package com.cnab.processor.controller;

import com.cnab.processor.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private TransactionService service;

    @GetMapping("/find")
    @Operation(
            description = "Find Transactions",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "findSuccess"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode = "400", ref = "badRequestFind")}
    )
    public ResponseEntity<Page> findTransacions
            (@RequestHeader(value = "companyName", required = false) String companyName,
             @RequestHeader(value = "companyId", required = false) String companyId,
             @RequestHeader(value = "accountOrigin", required = false) String accountOrigin,
             @RequestHeader(value = "accountDestination", required = false) String accountDestination,
             @RequestHeader(value = "type", required = false) String type,
             @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(
                service.findTransactions(page, companyName, companyId, accountOrigin, accountDestination, type), HttpStatus.OK);
    }


}
