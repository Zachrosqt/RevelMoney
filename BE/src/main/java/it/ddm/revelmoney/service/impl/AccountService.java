package it.ddm.revelmoney.service.impl;

import it.ddm.revelmoney.model.dto.AccountDto;
import it.ddm.revelmoney.model.mapper.AccountMapper;
import it.ddm.revelmoney.repository.AccountRepository;
import it.ddm.revelmoney.service.IAccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    public AccountService(final AccountRepository accountRepository, final AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto findInfoOfAccount() {
        return accountRepository.findByCustomerUsername(SecurityContextHolder.getContext().getAuthentication()
                                                                .getName()).map(accountMapper::toDto)
                .orElseThrow(
                        () -> new IllegalArgumentException("No account found with the provided customer Username"));
    }
}
