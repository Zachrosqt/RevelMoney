package it.ddm.revelmoney.service.impl;

import it.ddm.revelmoney.model.dto.CustomerDto;
import it.ddm.revelmoney.model.dto.TokenResponseDto;
import it.ddm.revelmoney.model.entities.Customer;
import it.ddm.revelmoney.model.mapper.CustomerMapper;
import it.ddm.revelmoney.repository.CustomerRepository;
import it.ddm.revelmoney.service.IAuthService;
import it.ddm.revelmoney.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    public AuthService(final AuthenticationManager authManager, final JwtUtil jwtUtil,
            final CustomerRepository customerRepository,
            final BCryptPasswordEncoder passwordEncoder,
            final CustomerMapper customerMapper) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerMapper = customerMapper;
    }

    @Override
    public TokenResponseDto login(final String username, final String password) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        return new TokenResponseDto(jwtUtil.generateToken(username));
    }

    @Override
    public void register(final CustomerDto customer) {
        customerRepository.findByUsername(customer.getUsername()).ifPresentOrElse(existingCustomer -> {
                throw new IllegalArgumentException("User with username " + existingCustomer.getUsername());
        }, () -> {
            Customer customerToSave = customerMapper.toEntity(customer);
            customerToSave.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customerToSave);
        });
    }
}
