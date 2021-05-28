package hu.webuni.logistic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistic.model.Address;
import hu.webuni.logistic.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public Address modifiyById(long id, Address modderAddress) throws Exception {
		Address address = addressRepository
								.findById(id)
								.orElseThrow(
								()-> new ResponseStatusException(HttpStatus.NOT_FOUND)); 
	
		address.setCity(modderAddress.getCity());
		address.setCodeISO(modderAddress.getCodeISO());
		address.setHauseNumber(modderAddress.getHauseNumber());
		address.setHorizontalCircle(modderAddress.getHorizontalCircle());
		address.setVerticalCircle(modderAddress.getVerticalCircle());
		address.setStreet(modderAddress.getStreet());
		address.setZipCode(modderAddress.getZipCode());
		
		Address updatedAddress = addressRepository.save(address);
		return updatedAddress;
	}

	public List<Address> searchAddressesByExample(Address example) {
		if(example == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		}
		
		long   id               = example.getId();
		String city             = example.getCity();
		String iso              = example.getCodeISO();
		int    hauseNumber      = example.getHauseNumber();
		double horizontalCircle = example.getHorizontalCircle();
		double verticalCircle   = example.getVerticalCircle();
		String street           = example.getStreet();
		int    zipCode          = example.getZipCode();
		
		Specification<Address> spec  = Specification.where(null);

		
		if(id >0) {
			spec = spec.and(AddressSpecification.hasId(id));
		}
		
		if(StringUtils.hasText(city)) {
			spec = spec.and(AddressSpecification.hasCity(city));
		}
		
		if(StringUtils.hasText(iso)) {
			spec = spec.and(AddressSpecification.hasIso(iso));
		}
		
		if(StringUtils.hasText(street)) {
			spec = spec.and(AddressSpecification.hasStreet(street));
		}
		
		if(hauseNumber>0) {
			spec = spec.and(AddressSpecification.hasHauseNumber(hauseNumber));
		}
		
		if(zipCode>0) {
			spec = spec.and(AddressSpecification.hasZipCode(zipCode));
		}
		
		if(horizontalCircle>0.0) {
			spec = spec.and(AddressSpecification.hasHorizontalCircle(horizontalCircle));
		}
		
		if(verticalCircle>0.0) {
			spec = spec.and(AddressSpecification.hasVerticalCircle(verticalCircle));
		}
		
		List<Address> returnList = addressRepository.findAll(spec, Sort.by("id"));
		
		if(returnList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		}
		
		return returnList;
	}

	public Page<Address> searchAddressesByExample(Pageable pageable, Address example) {
		if(example == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		}
		
		long   id               = example.getId();
		String city             = example.getCity();
		String iso              = example.getCodeISO();
		int    hauseNumber      = example.getHauseNumber();
		double horizontalCircle = example.getHorizontalCircle();
		double verticalCircle   = example.getVerticalCircle();
		String street           = example.getStreet();
		int    zipCode          = example.getZipCode();
		
		Specification<Address> spec  = Specification.where(null);

		
		if(id >0) {
			spec = spec.and(AddressSpecification.hasId(id));
		}
		
		if(StringUtils.hasText(city)) {
			spec = spec.and(AddressSpecification.hasCity(city));
		}
		
		if(StringUtils.hasText(iso)) {
			spec = spec.and(AddressSpecification.hasIso(iso));
		}
		
		if(StringUtils.hasText(street)) {
			spec = spec.and(AddressSpecification.hasStreet(street));
		}
		
		if(hauseNumber>0) {
			spec = spec.and(AddressSpecification.hasHauseNumber(hauseNumber));
		}
		
		if(zipCode>0) {
			spec = spec.and(AddressSpecification.hasZipCode(zipCode));
		}
		
		if(horizontalCircle>0.0) {
			spec = spec.and(AddressSpecification.hasHorizontalCircle(horizontalCircle));
		}
		
		if(verticalCircle>0.0) {
			spec = spec.and(AddressSpecification.hasVerticalCircle(verticalCircle));
		}
		
		List<Address> returnList   = addressRepository.findAll(spec, Sort.by("id"));
		Page<Address> pageableList = (Page<Address>) returnList;
		
		if(pageableList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		}
		
		return pageableList;
	}
}

