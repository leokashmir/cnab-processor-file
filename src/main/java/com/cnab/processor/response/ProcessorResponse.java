package com.cnab.processor.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessorResponse {

    private String status;
    private String message;
    private Data data;
}
