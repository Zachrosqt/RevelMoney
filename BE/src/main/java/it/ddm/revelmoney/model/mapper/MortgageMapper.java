package it.ddm.revelmoney.model.mapper;

import it.ddm.revelmoney.model.dto.MortgageDto;
import it.ddm.revelmoney.model.entities.Mortgage;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MortgageMapper {
    Mortgage toEntity(MortgageDto mortgageDto);

    MortgageDto toDto(Mortgage mortgage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Mortgage partialUpdate(
            MortgageDto mortgageDto, @MappingTarget Mortgage mortgage);
}