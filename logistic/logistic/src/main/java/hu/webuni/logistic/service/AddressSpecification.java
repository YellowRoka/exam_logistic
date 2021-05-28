package hu.webuni.logistic.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.logistic.model.Address;
import hu.webuni.logistic.model.Address_;

public class AddressSpecification {

	public static Specification<Address> hasId(long id) {
		return(root,cq,cb)->cb.equal(root.get(Address_.id),id);
	}

	public static Specification<Address> hasCity(String city) {
		return(root,cq,cb)->cb.equal(root.get(Address_.city),city+"%");
	}

	public static Specification<Address> hasIso(String iso) {
		return(root,cq,cb)->cb.equal(root.get(Address_.codeISO),iso);
	}

	public static Specification<Address> hasStreet(String street) {
		return(root,cq,cb)->cb.equal(root.get(Address_.street),street+"%");
	}

	public static Specification<Address> hasHauseNumber(int hauseNumber) {
		return(root,cq,cb)->cb.equal(root.get(Address_.hauseNumber),hauseNumber);
	}

	public static Specification<Address> hasZipCode(int zipCode) {
		return(root,cq,cb)->cb.equal(root.get(Address_.zipCode),zipCode);
	}

	public static Specification<Address> hasHorizontalCircle(double horizontalCircle) {
		return(root,cq,cb)->cb.equal(root.get(Address_.horizontalCircle),horizontalCircle);
	}

	public static Specification<Address> hasVerticalCircle(double verticalCircle) {
		return(root,cq,cb)->cb.equal(root.get(Address_.verticalCircle),verticalCircle);
	}


}
