package hu.webuni.logistic.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import hu.webuni.logistic.model.Address;
import hu.webuni.logistic.model.Milestone;
import hu.webuni.logistic.model.Section;
import hu.webuni.logistic.model.TransportPlan;
import hu.webuni.logistic.repository.AddressRepository;
import hu.webuni.logistic.repository.MilestoneRepository;
import hu.webuni.logistic.repository.SectionRepository;
import hu.webuni.logistic.repository.TransportPlanRepository;
import hu.webuni.logistic.security.LogisticUser;
import hu.webuni.logistic.security.UserRepository;


@Service
public class InitDBService {
	 
	TransportPlanRepository transportPlanRepository;
	MilestoneRepository     milestoneRepository;
	AddressRepository       addressRepository;
	SectionRepository       sectionRepository;
	UserRepository          userRepository;
	
	PasswordEncoder         passwordEncoder;
	

	public InitDBService(TransportPlanRepository transportPlanRepository, MilestoneRepository milestoneRepository,
			AddressRepository addressRepository, SectionRepository sectionRepository,UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.transportPlanRepository = transportPlanRepository;
		this.milestoneRepository     = milestoneRepository;
		this.addressRepository       = addressRepository;
		this.sectionRepository       = sectionRepository;
		this.userRepository          = userRepository;
		this.passwordEncoder         = passwordEncoder;
	}

	
	@Transactional
	public void clearDB() {
		sectionRepository.deleteAll();
		transportPlanRepository.deleteAll();
		milestoneRepository.deleteAll();
		addressRepository.deleteAll();	
		
		userRepository.deleteAll();
	}

	//@Transactional
	public TransportPlan insertTestData() {
		
		Address addressS1 = new Address("HU", "Budapest", "Orczi T??r",   1234, 8, 45.12, 90.24);
		Address addressS2 = new Address("HU", "Budapest", "Blaha T??r",   2345, 10, 40.00, 90.00);
		Address addressS3 = new Address("HU", "Budapest", "Oktogon T??r", 3456, 10, 40.00, 90.00);
		Address addressD  = new Address("HU", "HUN", "HUN T??r", 0000, 00, 00.00, 00.00);
		
			addressS1 = addressRepository.save(addressS1);
			addressS2 = addressRepository.save(addressS2);
			addressS3 = addressRepository.save(addressS3);
			
			addressD = addressRepository.save(addressD);
		
			
		Milestone milestoneStart1 = new Milestone(addressS1, LocalDateTime.of(2021, Month.JUNE, 01, 00, 00, 00) );
		Milestone milestoneEnd1   = new Milestone(addressS2, LocalDateTime.of(2021, Month.JUNE, 02, 00, 00, 00) );
		
		Milestone milestoneStart2 = new Milestone(addressS2, LocalDateTime.of(2021, Month.JUNE, 03, 00, 00, 00) );
		Milestone milestoneEnd2   = new Milestone(addressS3, LocalDateTime.of(2021, Month.JUNE, 04, 00, 00, 00) );
		
			milestoneStart1 = milestoneRepository.save(milestoneStart1);
			milestoneEnd1   = milestoneRepository.save(milestoneEnd1);
			milestoneStart2 = milestoneRepository.save(milestoneStart2);
			milestoneEnd2   = milestoneRepository.save(milestoneEnd2);
			
		List<Section> sections = new ArrayList<>();
		Section sec1 = new Section(milestoneStart1, milestoneEnd1, 2);
		sections.add(sec1);
		Section sec2 = new Section(milestoneStart2, milestoneEnd2, 2);
		sections.add(sec2);
		
			sections = sectionRepository.saveAll(sections);
	/*
		milestoneStart1.setSection4From(sections.get(0));
		milestoneEnd1.setSection4To(sections.get(0));
		milestoneStart2.setSection4From(sections.get(1));
		milestoneEnd2.setSection4To(sections.get(1));
	
			milestoneStart1 = milestoneRepository.save(milestoneStart1);
			milestoneEnd1   = milestoneRepository.save(milestoneEnd1);
			milestoneStart2 = milestoneRepository.save(milestoneStart2);
			milestoneEnd2   = milestoneRepository.save(milestoneEnd2);
	*/		
		//sections = sectionRepository.saveAll(sections);
			
		//TransportPlan tplan = new TransportPlan(30000, sections);
		TransportPlan tplan = transportPlanRepository.save(new TransportPlan(30000,sections));
			 
		sections.forEach(it->it.setTransportPlan(tplan));
			sections = sectionRepository.saveAll(sections);
			 
		return null;
	}

	@Transactional
	public void createUsersIfNeeded() {
		
		if(!userRepository.existsById("TransportManager")) {
			userRepository.save(new LogisticUser("TransportManager", passwordEncoder.encode("pass"), Set.of("TM","U") ));
		}
		
		if(!userRepository.existsById("AddressManager")) {
			userRepository.save(new LogisticUser("AddressManager", passwordEncoder.encode("pass"), Set.of("AM","U") ));
		}
		
		if(!userRepository.existsById("user")) {
			userRepository.save(new LogisticUser("user", passwordEncoder.encode("pass"), Set.of("U") ));
		}
	}
}
