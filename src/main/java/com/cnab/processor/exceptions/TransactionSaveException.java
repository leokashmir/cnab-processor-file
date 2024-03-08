package com.cnab.processor.exceptions;

import com.cnab.processor.exceptions.response.ExceptionHandleResponse;

public class TransactionSaveException extends RuntimeException{

    private static final String MESSAGE_ERRO = "Nenhuma transação pode ser salva.";
    private static final String ERRO = "Erro";

    private ExceptionHandleResponse exceptionHandleResponse;

    public ExceptionHandleResponse getExceptionHandleResponse(){
        return ExceptionHandleResponse.builder()
                .status(ERRO)
                .message(MESSAGE_ERRO)
                .build();
    }
}
