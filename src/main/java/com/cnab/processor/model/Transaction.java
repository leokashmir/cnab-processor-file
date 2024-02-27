package com.cnab.processor.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_COMPANY", nullable=false)
    private Company company;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "AMOUNT")
    private BigDecimal value;

    @Column(name = "ACC_ORIGIN")
    private String accountOrigin;

    @Column(name = "ACC_DESTINATION")
    private String accountDestination;
}
