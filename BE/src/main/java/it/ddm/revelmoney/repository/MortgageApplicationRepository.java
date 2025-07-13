package it.ddm.revelmoney.repository;

import it.ddm.revelmoney.model.entities.MortgageApplication;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MortgageApplicationRepository extends JpaRepository<MortgageApplication, Long> {
}
