package it.ddm.revelmoney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for {@link it.ddm.revelmoney.model.entities.Mortgage}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MortgageDto implements Serializable {
    Long id;
    String name;
    String description;
    Double maxAmount;
    Double maxLtvRatio;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    Double interestAmount;
}