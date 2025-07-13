package it.ddm.revelmoney.service.impl;

import it.ddm.revelmoney.model.dto.MortgageDto;
import it.ddm.revelmoney.model.dto.MortgageApplicationRequestDto;
import it.ddm.revelmoney.model.entities.MortgageApplication;
import it.ddm.revelmoney.model.mapper.MortgageApplicationMapper;
import it.ddm.revelmoney.model.mapper.MortgageMapper;
import it.ddm.revelmoney.repository.CustomerRepository;
import it.ddm.revelmoney.repository.MortgageApplicationRepository;
import it.ddm.revelmoney.repository.MortgageRepository;
import it.ddm.revelmoney.service.IMortgageService;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MortgageService implements IMortgageService {

    private final MortgageRepository mortgageRepository;

    private final MortgageMapper mortgageMapper;

    private final MortgageApplicationRepository mortgageApplicationRepository;

    private final CustomerRepository customerRepository;

    private final MortgageApplicationMapper mortgageApplicationMapper;

    public MortgageService(final MortgageRepository mortgageRepository,
            final MortgageMapper mortgageMapper,
            final MortgageApplicationRepository mortgageApplicationRepository,
            final CustomerRepository customerRepository,
            final MortgageApplicationMapper mortgageApplicationMapper) {
        this.mortgageRepository = mortgageRepository;
        this.mortgageMapper = mortgageMapper;
        this.mortgageApplicationRepository = mortgageApplicationRepository;
        this.customerRepository = customerRepository;
        this.mortgageApplicationMapper = mortgageApplicationMapper;
    }

    @Override
    public List<MortgageDto> mortgageList() {
        return mortgageRepository.findAll().stream().map(mortgageMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void applyForMortgage(final MortgageApplicationRequestDto mortgageApplicationRequest) {
        customerRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .ifPresentOrElse(customer -> {
                    mortgageRepository.findById(mortgageApplicationRequest.getMortgageId())
                            .ifPresentOrElse(mortgage -> {
                                MortgageApplication mortgageApplication =
                                        mortgageApplicationMapper.toEntity(mortgageApplicationRequest);
                                mortgageApplication.setCustomer(customer);
                                mortgageApplication.setMortgage(mortgage);
                                mortgageApplication = mortgageApplicationRepository.save(mortgageApplication);
                                customer.getMortgageApplications().add(mortgageApplication);
                                customerRepository.save(customer);
                            }, () -> {
                                throw new IllegalArgumentException("Mortgage not found");
                            });
                }, () -> {
                    throw new IllegalArgumentException("Customer not found");
                });
    }
}
