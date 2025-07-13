package it.ddm.revelmoney.service;

import it.ddm.revelmoney.model.dto.CustomerDto;
import it.ddm.revelmoney.model.dto.TokenResponseDto;
public interface IAuthService {
    TokenResponseDto login(String username, String password);
    void register(CustomerDto customer);
}
