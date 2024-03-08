package com.cnab.processor.model;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_COMPANY")
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
