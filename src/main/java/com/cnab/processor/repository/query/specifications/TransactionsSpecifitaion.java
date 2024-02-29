package com.cnab.processor.repository.query.specifications;

import com.cnab.processor.model.Company;
import com.cnab.processor.model.Transaction;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TransactionsSpecifitaion {

    private Transaction transaction;

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
