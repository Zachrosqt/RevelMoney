package it.ddm.revelmoney.controller;

import it.ddm.revelmoney.model.dto.MortgageDto;
import it.ddm.revelmoney.model.dto.MortgageApplicationRequestDto;
import it.ddm.revelmoney.service.impl.MortgageService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Mortgage Controller")
@RequestMapping(value = "/mortgage")
public class MortgageController {

    private final MortgageService mortgageService;

    public MortgageController(final MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MortgageDto>> mortgageList() {
        return ResponseEntity.ok(mortgageService.mortgageList());
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyForMortgage(
            @RequestBody final MortgageApplicationRequestDto mortgageApplicationDto) {
        try {
            mortgageService.applyForMortgage(mortgageApplicationDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
