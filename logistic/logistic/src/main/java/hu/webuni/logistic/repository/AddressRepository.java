package hu.webuni.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.logistic.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>,JpaSpecificationExecutor<Address>{

}
