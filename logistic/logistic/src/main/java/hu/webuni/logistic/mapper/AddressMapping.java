package hu.webuni.logistic.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.logistic.dto.AddressDto;
import hu.webuni.logistic.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapping {

	public Address dtoToModel(AddressDto addressDto);

	public AddressDto modelToDto(Address address);

	public List<AddressDto> modelsToDtos(List<Address> addresses);
	
	//public List<Address> dtosToModel(List<AddressDto> addressesDto);

}
