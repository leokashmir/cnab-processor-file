package com.cnab.processor.enumerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionErroEnum {

    IDENTIFICACAO_TIPO_REGISTRO("Identificação do tipo de registro"),
    TIPO_TRANSACAO("Tipo de transação (C para Crédito, D para Débito, T para Transferência)"),
    VALOR_TRANSACAO("Valor da transaçã (formato decimal, sem ponto decimal)"),
    CONTA_ORIGEM("Conta origem"),
    CONTA_DESTINO("Conta destino");


    private final String value;
}
