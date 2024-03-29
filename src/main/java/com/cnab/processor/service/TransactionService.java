package com.cnab.processor.service;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import com.cnab.processor.repository.TransactionsRepository;
import com.cnab.processor.repository.query.specifications.TransactionsSpecifitaion;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Log4j2
@Service
public class TransactionService {

    @Autowired
    private TransactionsRepository repository;
    private TransactionsSpecifitaion specifitaion;

    /**
     * Busca transações com base nos critérios fornecidos.
     *
     * @param page   O objeto Pageable para controlar a paginação dos resultados.
     * @param filter Um array de strings contendo os critérios de filtragem.
     *               Os critérios devem estar na seguinte ordem:
     *               [0] - companyName.   (Nome da Empresa)
     *               [1] - companyId.     (Cnpj)
     *               [2] - accountOrigin.   (Conta de Origem)
     *               [3] - accountDestination. ( Conta de destino)
     *               [4] - type.  (Tipo de Operação)
     * @return Uma lista de transações que correspondem aos critérios de filtragem e paginadas de acordo com o objeto Pageable.
     */
    public Page findTransactions(Pageable page, String... filter) {


        Transaction transaction =
                Transaction.builder()
                        .company(Company.of(null, filter[0], filter[1]))
                        .accountOrigin(filter[2])
                        .accountDestination(filter[3])
                        .type(filter[4])
                        .build();

        specifitaion = new TransactionsSpecifitaion(transaction);


        return repository.findAll(specifitaion.dinamicQuery(), page);
    }

}
