package com.cnab.processor.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder @Getter
public class TransactionDto {

    private String type;
    private BigDecimal value;
    private String accountOrigin;
    private String accountDestination;
}
