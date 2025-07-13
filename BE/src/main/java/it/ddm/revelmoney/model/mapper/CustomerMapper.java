package it.ddm.revelmoney.model.mapper;

import it.ddm.revelmoney.model.dto.CustomerDto;
import it.ddm.revelmoney.model.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    
    Customer toEntity(CustomerDto customerDto);
    CustomerDto toDto(Customer customer);
}