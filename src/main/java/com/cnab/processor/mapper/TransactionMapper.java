package com.cnab.processor.mapper;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.util.AbstractMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TransactionMapper extends AbstractMapper<TransactionDto, Transaction> {

    @Override
    public List<Transaction> mapList(List<TransactionDto> dtoList, Class<Transaction> transaction) {
            return super.mapList(dtoList, transaction);
    }

}


