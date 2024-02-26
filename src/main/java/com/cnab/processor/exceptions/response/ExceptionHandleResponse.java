package com.cnab.processor.exceptions.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionHandleResponse {

    private String status;
    private String message;
    private List<ErroFile> erros;

}
