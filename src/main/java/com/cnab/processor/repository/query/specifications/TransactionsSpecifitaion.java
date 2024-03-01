package com.cnab.processor.repository.query.specifications;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@AllArgsConstructor
public class TransactionsSpecifitaion {

    private Transaction transaction;

    /**
     * Constrói uma especificação de consulta dinâmica para transações com base nos critérios fornecidos.
     *
     * @return Uma especificação de consulta para transações com os critérios fornecidos.
     */
    public Specification<Transaction> dinamicQuery(){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList();

            if(transaction.getCompany().getCompanyName() != null){
                Path<Company> company = root.<Company>get("company");
                Path<String>  campoCompanyName = company.get("companyName");
                Predicate predicateCompanyName = builder.like(campoCompanyName , "%" + transaction.getCompany().getCompanyName() + "%");
                predicates.add(predicateCompanyName);
            }
            if(transaction.getType() != null){
                Path<String> campoType = root.<String>get("type");
                Predicate predicateType = builder.equal(campoType,transaction.getType() );
                predicates.add(predicateType);
            }
            if(transaction.getAccountOrigin() != null){
                Path<String> campoAccountOrigin = root.<String>get("accountOrigin");
                Predicate predicateAccOrgin = builder.like(campoAccountOrigin, "%" +  transaction.getType() + "%" );
                predicates.add(predicateAccOrgin);
            }
            if(transaction.getAccountDestination() != null){
                Path<String> campoAccountDestination = root.<String>get("accountDestination");
                Predicate predicateAccDestiny = builder.like(campoAccountDestination,"%" +  transaction.getType() + "%" );
                predicates.add(predicateAccDestiny);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
