package it.ddm.revelmoney.service;

import it.ddm.revelmoney.model.dto.MortgageDto;
import it.ddm.revelmoney.model.dto.MortgageApplicationRequestDto;
import java.util.List;
public interface IMortgageService {
    List<MortgageDto> mortgageList();
    void applyForMortgage(MortgageApplicationRequestDto mortgage);
}
