package it.ddm.revelmoney.repository;

import it.ddm.revelmoney.model.entities.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MortgageRepository extends JpaRepository<Mortgage, Long> {
}
