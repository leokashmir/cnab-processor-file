package com.cnab.processor.mapper;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.util.AbstractMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.List;
@Log4j2
@Component
public class TransactionMapper extends AbstractMapper<TransactionDto, Transaction> {


    /**
     * Mapeia uma lista de objetos do tipo TransactionDto para uma lista de objetos do tipo Transaction.
     *
     * @param dtoList A lista de objetos do tipo TransactionDto a ser mapeada.
     * @param transaction A classe que representa o tipo Transaction para a qual os objetos devem ser mapeados.
     * @return Uma lista de objetos do tipo Transaction resultante do mapeamento.
     */
    @Override
    public List<Transaction> mapList(List<TransactionDto> dtoList, Class<Transaction> transaction) {
            return super.mapList(dtoList, transaction);
    }

}


