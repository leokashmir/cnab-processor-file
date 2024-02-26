package com.cnab.processor.exceptions;

import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.exceptions.response.ExceptionHandleResponse;
import java.util.List;
public class TransactionException extends RuntimeException{


    private static final String MESSAGE_ERRO = "O arquivo CNAB possui formato inválido.";
    private static final String ERRO = "Erro";

    private List<ErroFile> erros;
    private ExceptionHandleResponse exceptionHandleResponse;

    public TransactionException(List<ErroFile> erros) {
        this.erros = erros;
    }

    public ExceptionHandleResponse getExceptionHandleResponse(){
         return ExceptionHandleResponse.builder()
                 .status(ERRO)
                 .message(MESSAGE_ERRO)
                 .erros(erros)
                 .build();
    }
}
