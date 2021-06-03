package hu.webuni.logistic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import hu.webuni.logistic.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>,JpaSpecificationExecutor<Address>{

	//public Page<Address> findAll(PageRequest pageable);
	
	//@EntityGraph(attributePaths = {"address"})
	//@Query("SELECT a FROM Address a")
	//public Page<Address> findAll(PageRequest pageable,Specification<?> spec);
	
	@Query("SELECT a FROM Address a")
	public Page<Address> findAll(Pageable pageable,Specification<?> spec);
	
	 //@Query("SELECT a FROM Address a where a.movie = :movie")
	@Query("SELECT a FROM Address a ")
	 public Page<Address> findByParams(Pageable pageable,Specification<?> spec);
	      //@Param("movie") String movieName, Sort sort);
}
