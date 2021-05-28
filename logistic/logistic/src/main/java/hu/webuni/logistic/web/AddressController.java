package hu.webuni.logistic.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistic.dto.AddressDto;
import hu.webuni.logistic.mapper.AddressMapping;
import hu.webuni.logistic.model.Address;
import hu.webuni.logistic.repository.AddressRepository;
import hu.webuni.logistic.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressMapping addressMapping;
	
	@Autowired
	AddressService addressService;

	@PutMapping("/{id}")
	public AddressDto modifyAddress(@PathVariable long id,  
									@RequestBody AddressDto addressDto) {
	
		if( (addressDto == null)||
		    (addressDto.getId() != id)||
		    (
		      (addressDto.getCity()        == null) &&
		      (addressDto.getZipCode()     == 0)    &&
		      (addressDto.getCodeISO()     == null) &&
		      (addressDto.getStreet()      == null) &&
		      (addressDto.getHauseNumber() == 0)
		    )
		  ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Address modderAddress = addressMapping.dtoToModel(addressDto);
		Address newAddress    = null;
		try {
			newAddress = addressService.modifiyById(id, modderAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressMapping.modelToDto(newAddress);
	}
	/*
	@PostMapping("/search")
	public List<AddressDto> searchAddressesByExample(@RequestBody AddressDto addressDto){
		Address       example   = addressMapping.dtoToModel(addressDto);	
		List<Address> addresses = addressService.searchAddressesByExample(example);
		
		return addressMapping.modelsToDtos(addresses);
	}
	*/
	
	// http://localhost:8080/api/addresses/search?page=0&size=1
	@PostMapping("/search")
	public Page<AddressDto> searchAddressesByExample(@RequestBody AddressDto addressDto,Pageable pageable){
		Address       example   = addressMapping.dtoToModel(addressDto);	
		Page<Address> addresses = addressService.searchAddressesByExample(pageable,example);
		
		return addresses.map(addressMapping::modelToDto);
	}
}
