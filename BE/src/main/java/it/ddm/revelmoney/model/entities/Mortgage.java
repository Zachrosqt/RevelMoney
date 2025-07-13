package it.ddm.revelmoney.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Entity(name = "mortgage")
@Table(name = "mortgage")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mortgage {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mortgage_seq_gen"
    )
    @SequenceGenerator(
            name = "mortgage_seq_gen",
            sequenceName = "mortgage_seq",
            allocationSize = 1)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "max_amount")
    private Double maxAmount;

    @Column(name = "max_ltv_ratio")
    private Double maxLtvRatio;
    
    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

    @Column(name = "interest_amount")
    private Double interestAmount;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "mortgage")
    private List<MortgageApplication> mortgageApplications = new ArrayList<>();

}
