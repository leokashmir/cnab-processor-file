package com.cnab.processor.exceptions;

import com.cnab.processor.exceptions.response.ExceptionHandleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.charset.MalformedInputException;


@ControllerAdvice
public class ProcessorExceptionHandler {

    private static final String ERRO = "Erro";
    private static final String ERRO_PROCESS = "Ocorreu um erro durante o processamento do arquivo. ";
    private static final String INVALID_FILE = "Formato de aquivo invalid. O aquivo deve ser no formato de texto.";
    private static final String INVALID_FILE_SIZE = "O tamanho do aquivo não pode ultrapassar 5MB";

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionHandleResponse> handleTransaction(TransactionException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getExceptionHandleResponse());
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ExceptionHandleResponse> handleInvalidFile(InvalidFileException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getExceptionHandleResponse());
    }

    @ExceptionHandler(TransactionSaveException.class)
    public ResponseEntity<ExceptionHandleResponse> handleTrsanactionSaveErro(InvalidFileException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getExceptionHandleResponse());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHandleResponse> handleException(InvalidFileException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getExceptionHandleResponse());
    }

    @ExceptionHandler(MalformedInputException.class)
    public ResponseEntity<ExceptionHandleResponse> handleMalformedInputException(MalformedInputException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionHandleResponse.builder()
                        .status(ERRO)
                        .message(INVALID_FILE)
                .build());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionHandleResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionHandleResponse.builder()
                .status(ERRO)
                .message(INVALID_FILE_SIZE)
                .build());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionHandleResponse> handleThrowable(Throwable exception, WebRequest request) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandleResponse.builder()
                            .status(ERRO)
                            .message(ERRO_PROCESS.concat(exception.getMessage()))
                    .build());

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionHandleResponse> handleRuntime(RuntimeException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandleResponse.builder()
                .status(ERRO)
                .message(ERRO_PROCESS.concat(exception.getMessage()))
                .build());

    }
}
