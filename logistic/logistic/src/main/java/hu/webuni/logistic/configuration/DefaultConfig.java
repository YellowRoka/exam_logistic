package hu.webuni.logistic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.logistic.repository.AddressRepository;
import hu.webuni.logistic.repository.MilestoneRepository;
import hu.webuni.logistic.repository.SectionRepository;
import hu.webuni.logistic.repository.TransportPlanRepository;
import hu.webuni.logistic.service.AddressService;

@Configuration
@Profile("default")
public class DefaultConfig {

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository     milestoneRepository;
	
	@Autowired
	AddressRepository       addressRepository;
	
	@Autowired
	SectionRepository       sectionRepository;
	
	@Autowired
	AddressService          addressService;
	
}
