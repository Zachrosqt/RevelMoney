package it.ddm.revelmoney.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for {@link it.ddm.revelmoney.model.entities.Customer}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements Serializable {
    String username;
    String password;
    LocalDateTime birthDate;
    String firstName;
    String lastName;
    String email;
    String phone;
    String fiscalCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}