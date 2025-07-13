package it.ddm.revelmoney.model.mapper;

import it.ddm.revelmoney.model.dto.AccountDto;
import it.ddm.revelmoney.model.dto.MortgageApplicationDto;
import it.ddm.revelmoney.model.entities.Account;
import it.ddm.revelmoney.model.entities.MortgageApplication;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    
    Account toEntity(AccountDto accountDto);

    @AfterMapping
    default void linkTransactions(@MappingTarget Account account) {
        account.getTransactions().forEach(transaction -> transaction.setAccount(account));
    }

    @Mapping(source = "customer.mortgageApplications", target = "mortgageApplications", qualifiedByName = "toMorgagesDto")
    AccountDto toDto(Account account);

    @Named("toMorgagesDto")
    static List<MortgageApplicationDto> toMorgagesDto(List<MortgageApplication> mortgageApplications) {
        return mortgageApplications.stream()
                .map(mortgageApplication -> MortgageApplicationDto.builder().id(mortgageApplication.getId())
                        .interestType(mortgageApplication.getInterestType())
                        .interestAmount(mortgageApplication.getMortgage().getInterestAmount())
                        .name(mortgageApplication.getMortgage().getName())
                        .appliedAmount(mortgageApplication.getAppliedAmount())
                        .mortgageStatus(mortgageApplication.getMortgageStatus().name()).build())
                .collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(
            AccountDto accountDto, @MappingTarget Account account);
}