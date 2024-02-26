package com.cnab.processor.process;

import com.cnab.processor.exceptions.TransactionException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.response.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProcessorFile {


    public void checkErro( List<ErroFile> lstErroFile){
        if(lstErroFile != null && !lstErroFile.isEmpty()){
            throw new TransactionException(lstErroFile);
        }
    }

    public void saveTransactions(String idEmpresa,List<Transaction> transactionList ){

    }

}
