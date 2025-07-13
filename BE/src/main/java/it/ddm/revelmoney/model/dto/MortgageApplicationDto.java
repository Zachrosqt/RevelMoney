package it.ddm.revelmoney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.ddm.revelmoney.model.entities.MortgageApplication;
import it.ddm.revelmoney.model.enums.InterestType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
/**
 * DTO for {@link MortgageApplication}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MortgageApplicationDto implements Serializable {
    Long id;
    Double appliedAmount;
    InterestType interestType;
    Double interestAmount;
    String name;
    String mortgageStatus;
}