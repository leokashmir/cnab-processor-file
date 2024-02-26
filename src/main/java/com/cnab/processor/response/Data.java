package com.cnab.processor.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder @Getter
public class Data {

    private List<Transaction> transactions;
}
