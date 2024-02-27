package com.cnab.processor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name="COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "COMPANY_ID")
    private String companyId;

    @OneToMany (mappedBy="company")
    private List<Transaction> transactionList;


}
