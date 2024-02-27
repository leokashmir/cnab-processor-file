package com.cnab.processor.process;

import com.cnab.processor.exceptions.TransactionException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.model.Company;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProcessorFile {


    public void checkErro( List<ErroFile> lstErroFile){
        if(lstErroFile != null && !lstErroFile.isEmpty()){
            throw new TransactionException(lstErroFile);
        }
    }

    public void saveTransactions(Company company, List<TransactionDto> transactionDtoList){
            System.out.println(company.getCompanyName());
    }

}
