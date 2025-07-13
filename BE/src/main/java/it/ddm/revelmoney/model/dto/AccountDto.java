package it.ddm.revelmoney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for {@link it.ddm.revelmoney.model.entities.Account}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto implements Serializable {
    Long id;
    String iban;
    Double balance;
    String currency;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<TransactionDto> transactions;
    List<MortgageApplicationDto> mortgageApplications;
}