package it.ddm.revelmoney.model.entities;

import it.ddm.revelmoney.model.enums.InterestType;
import it.ddm.revelmoney.model.enums.MortgageStatus;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name = "mortgage_application")
@Table(name = "mortgage_application")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MortgageApplication {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mortgage_application_seq_gen"
    )
    @SequenceGenerator(
            name = "mortgage_application_seq_gen",
            sequenceName = "mortgage_application_seq",
            allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_username")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "mortgage_id")
    private Mortgage mortgage;
    
    @Column(name = "applied_amount")
    private Double appliedAmount;

    @Column(name = "interest_type")
    @Enumerated(EnumType.STRING)
    private InterestType interestType;
    
    @Column(name = "interest_amount")
    private Double interestAmount;
    
    @Column(name = "mortgage_status")
    @Enumerated(EnumType.STRING)
    private MortgageStatus mortgageStatus;
}
