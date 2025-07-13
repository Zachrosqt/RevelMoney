package it.ddm.revelmoney.controller;


import it.ddm.revelmoney.model.dto.AccountDto;
import it.ddm.revelmoney.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController(value = "Account Controller")
@RequestMapping(value = "/account")
public class AccountController {
    private final IAccountService accountService;
    public AccountController(final IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/info")
    public ResponseEntity<AccountDto> infoOfAccount() {
        return ResponseEntity.ok(accountService.findInfoOfAccount());
    }
}

