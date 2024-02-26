package com.cnab.processor.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HeaderErroEnum {

    IDENTIFICACAO_TIPO_REGISTRO("Identificação do tipo de registro invalido"),
    RAZAO_SOCIAL("Registro de Razão social da empresa invalido"),
    RAZAO_SOCIAL_VAZIO("Registro de Razão social da empresa esta em branco"),
    IDENTIFICADOR_EMPRESA("Identificador da empresa invalido"),
    ESPACO_RESERVADO_FUTURO("Espaço reservado para uso futuro"),
    TAMANHO_REGISTRO("Tamanho do registro está incorreto");

    private final String value;
}
