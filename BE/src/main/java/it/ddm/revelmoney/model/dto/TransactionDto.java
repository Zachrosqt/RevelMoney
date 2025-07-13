package it.ddm.revelmoney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.ddm.revelmoney.model.enums.TransactionType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for {@link it.ddm.revelmoney.model.entities.Transaction}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto implements Serializable {
    Long id;
    Double amount;
    TransactionType type;
    String description;
    LocalDateTime timestamp;
}