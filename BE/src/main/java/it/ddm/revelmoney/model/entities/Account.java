package it.ddm.revelmoney.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "account")
@Table(name = "account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_seq_gen"
    )
    @SequenceGenerator(
            name = "account_seq_gen",
            sequenceName = "account_seq",
            allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @Column(name = "iban", nullable = false)
    private String iban;
    
    @Column(name = "balance", nullable = false)
    private Double balance;
    
    @Column(name = "currency", nullable = false)
    private String currency = "EUR";
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

}
