package com.cnab.processor.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FootErroEnum {

    IDENTIFICACAO_TIPO_REGISTRO("Identificação do tipo de registro invalido"),
    ESPACO_RESERVADO_CONTROLE("Espaço reservado para informações de totalização ou controle"),
    TAMANHO_REGISTRO("Tamanho do registro está incorreto");

    private final String value;
}
