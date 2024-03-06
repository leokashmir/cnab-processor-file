package com.cnab.processor.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionErroEnum {

    IDENTIFICACAO_TIPO_REGISTRO("Identificação do tipo de registro"),
    TIPO_TRANSACAO("Tipo de transação inválido"),
    VALOR_TRANSACAO("Valor da transação não pode ser nulo ou zerado"),
    FORMATO_TRANSACAO("Valor da transação está fora do formato válido."),
    CONTA_ORIGEM("Conta origem é obrigatória."),
    CONTA_DESTINO("Conta destino é obrigatória");


    private final String value;
}
