package hu.webuni.logistic.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.logistic.repository.AddressRepository;
import hu.webuni.logistic.repository.MilestoneRepository;
import hu.webuni.logistic.repository.SectionRepository;
import hu.webuni.logistic.repository.TransportPlanRepository;

@Service
public class InitDBService {
	 
	TransportPlanRepository transportPlanRepository;
	MilestoneRepository     milestoneRepository;
	AddressRepository       addressRepository;
	SectionRepository       sectionRepository;
	

	public InitDBService(TransportPlanRepository transportPlanRepository, MilestoneRepository milestoneRepository,
			AddressRepository addressRepository, SectionRepository sectionRepository) {
		super();
		this.transportPlanRepository = transportPlanRepository;
		this.milestoneRepository = milestoneRepository;
		this.addressRepository = addressRepository;
		this.sectionRepository = sectionRepository;
	}

	
	@Transactional
	public void clearDB() {
		 transportPlanRepository.deleteAll();
		 milestoneRepository.deleteAll();
		 addressRepository.deleteAll();
		 sectionRepository.deleteAll();
		
	}

	public void insertTestData() {
		// TODO Auto-generated method stub
		
	}
}
