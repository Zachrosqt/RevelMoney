package it.ddm.revelmoney.controller;

import it.ddm.revelmoney.model.dto.AuthenticationRequestDto;
import it.ddm.revelmoney.model.dto.CustomerDto;
import it.ddm.revelmoney.model.dto.TokenResponseDto;
import it.ddm.revelmoney.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final IAuthService authService;

    public AuthenticationController(final IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(final @RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerDto request) {
        authService.register(request);
        return ResponseEntity.ok("Registered user with username " + request.getUsername());
    }
}
