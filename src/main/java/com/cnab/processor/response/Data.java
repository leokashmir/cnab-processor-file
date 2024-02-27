package com.cnab.processor.response;

import com.cnab.processor.dto.TransactionDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private List<TransactionDto> transactionDtos;
}
