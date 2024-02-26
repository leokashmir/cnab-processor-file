package com.cnab.processor.exceptions.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ErroFile {

    private String line;
    private String error;
}
