package it.ddm.revelmoney.model.mapper;

import it.ddm.revelmoney.model.dto.MortgageApplicationDto;
import it.ddm.revelmoney.model.dto.MortgageApplicationRequestDto;
import it.ddm.revelmoney.model.entities.MortgageApplication;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MortgageApplicationMapper {
    MortgageApplication toEntity(MortgageApplicationDto mortgageApplicationDto);

    MortgageApplication toEntity(MortgageApplicationRequestDto mortgageApplicationRequestDto);
    
    MortgageApplicationDto toDto(MortgageApplication mortgageApplication);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MortgageApplication partialUpdate(
            MortgageApplicationDto mortgageApplicationDto, @MappingTarget MortgageApplication mortgageApplication);
}