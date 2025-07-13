package it.ddm.revelmoney.repository;

import it.ddm.revelmoney.model.entities.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    Optional<Account> findByCustomerUsername(String customerUsername);
    
}