package com.cnab.processor.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class Transaction {

    private String type;
    private Double value;
    private String accountOrigin;
    private String accountDestination;
}
