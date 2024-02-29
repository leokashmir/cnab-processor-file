package com.cnab.processor.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

import static org.hibernate.annotations.CascadeType.PERSIST;

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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", company=" + company +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", accountOrigin='" + accountOrigin + '\'' +
                ", accountDestination='" + accountDestination + '\'' +
                '}';
    }
}
