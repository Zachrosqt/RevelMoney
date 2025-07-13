package it.ddm.revelmoney.model.entities;

import it.ddm.revelmoney.model.enums.TransactionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name = "transaction")
@Table(name = "transaction")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_seq_gen"
    )
    @SequenceGenerator(
            name = "transaction_seq_gen",
            sequenceName = "transaction_seq",
            allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Account account;
}
