package com.cnab.processor.exceptions;

import com.cnab.processor.exceptions.response.ExceptionHandleResponse;
import lombok.AllArgsConstructor;


public class InvalidFileException extends RuntimeException{


    private static final String MESSAGE_ERRO = "Erro ao processar o arquivo CNAB. Certifique-se de que o arquivo esteja no formato posicional correto.";
    private static final String ERRO = "Erro";
    private ExceptionHandleResponse exceptionHandleResponse;

    public ExceptionHandleResponse getExceptionHandleResponse(){
        return ExceptionHandleResponse.builder()
                .status(ERRO)
                .message(MESSAGE_ERRO)
                .build();
    }

}
