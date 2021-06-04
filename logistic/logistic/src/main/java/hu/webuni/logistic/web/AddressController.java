package hu.webuni.logistic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import hu.webuni.logistic.service.AddressService;
import hu.webuni.logistic.service.ExtendedPage;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressMapping addressMapping;
	
	@Autowired
	AddressService addressService;

	@PostMapping
	public AddressDto addAddress(@RequestBody AddressDto newAddressDto) {
		
		Address newAddress   = addressMapping.dtoToModel(newAddressDto); 
		Address addedAddress = addressService.addAddress(newAddress);
		
		return addressMapping.modelToDto(addedAddress);
	}
	
	@GetMapping
	public List<AddressDto> getAllAddress(){
		return addressMapping.modelsToDtos(
								addressService.getAllAddress());
	}
	
	@GetMapping("/{id}")
	public AddressDto getAddressById(@PathVariable long id) {
		Address address    = addressService.getAddressById(id);
		AddressDto gettedAddress = addressMapping.modelToDto(address);
		return gettedAddress;
	}
	
	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.deleteAddressById(id);
	}
	
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
	
	//sample: http://localhost:8080/api/addresses/search?page=0&size=1,asc
	@PostMapping("/search")
	public ExtendedPage<AddressDto> searchAddressesByExample( @RequestBody AddressDto addressDto,
													  @SortDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
		
		Address       example   = addressMapping.dtoToModel(addressDto);	
		
		Page<Address> addresses = addressService.searchAddressesByExamplePaged(pageable, example/*, sortBy*/);

		Page<AddressDto> retMap = addresses.map(addressMapping::modelToDto);
		
		ExtendedPage<AddressDto> EP = new ExtendedPage<AddressDto>();
		EP.setPages(retMap);
		EP.setX_Total_Count(retMap.getTotalElements());
		
		return EP;
	}
}
