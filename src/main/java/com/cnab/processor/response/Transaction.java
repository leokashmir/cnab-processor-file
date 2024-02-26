package com.cnab.processor.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder @Getter
public class Transaction {

    private String type;
    private BigDecimal value;
    private String accountOrigin;
    private String accountDestination;
}
