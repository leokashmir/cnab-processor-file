package com.cnab.processor.process;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.exceptions.TransactionException;
import com.cnab.processor.exceptions.TransactionSaveException;
import com.cnab.processor.exceptions.response.ErroFile;
import com.cnab.processor.mapper.TransactionMapper;
import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.repository.CompanyRepository;
import com.cnab.processor.repository.TransactionsRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class ProcessorFile {

    private TransactionsRepository repository;
    private CompanyRepository companyRepository;
    private TransactionMapper mapper;

    public void checkErro(List<ErroFile> lstErroFile) {
        if (lstErroFile != null && !lstErroFile.isEmpty()) {
            throw new TransactionException(lstErroFile);
        }
    }
    @Transactional @SneakyThrows(TransactionSaveException.class)
    public void saveTransactions(Company companyToSave, List<TransactionDto> transactionDtoList) {
       try {
           List<Transaction> transactions = mapper.mapList(transactionDtoList, Transaction.class);
           Company company = companyRepository.findByCompanyId(companyToSave.getCompanyId());

           if (company == null) {
               company = companyRepository.save(companyToSave);
           }

           for (Transaction t : transactions) {
               t.setCompany(company);
           }
           repository.saveAll(transactions);

       }catch (Exception e){
           throw new TransactionSaveException();
       }
    }

}
