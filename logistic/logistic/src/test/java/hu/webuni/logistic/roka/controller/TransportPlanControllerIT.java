package hu.webuni.logistic.roka.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.logistic.mapper.TransportPlanMapper;
import hu.webuni.logistic.model.Address;
import hu.webuni.logistic.model.Milestone;
import hu.webuni.logistic.model.Section;
import hu.webuni.logistic.model.TransportPlan;
import hu.webuni.logistic.repository.AddressRepository;
import hu.webuni.logistic.repository.MilestoneRepository;
import hu.webuni.logistic.repository.SectionRepository;
import hu.webuni.logistic.repository.TransportPlanRepository;
import hu.webuni.logistic.service.TransportPlanService;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportPlanControllerIT {
	
/* ---------------------------- INIT ENVIRONMENT ----------------------------*/
	private static final String BASE_URI = "/api/transportPlans/";
	
	@Autowired
	WebTestClient WebTestClient;
	
	
	@Value("${logistic.delay.MIN_LOW}")
	private double MIN_LOW_VALUE;
	@Value("${logistic.delay.MIN_MED}")
	private double MIN_MED_VALUE;
	@Value("${logistic.delay.MIN_HIGH}")
	private double MIN_HIGH_VALUE;
	
	@Value("${logistic.delay.MIN_LOW_PERCENT}")
	private double MIN_LOW_PERCENT;
	@Value("${logistic.delay.MIN_MED_PERCENT}")
	private double MIN_MED_PERCENT;
	@Value("${logistic.delay.MIN_HIGH_PERCENT}")
	private double MIN_HIGH_PERCENT;
	
	
	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository     milestoneRepository;
	
	@Autowired
	AddressRepository       addressRepository;
	
	@Autowired
	SectionRepository       sectionRepository;
	
	@Autowired
	TransportPlanService   transportPlanService;
	
	@Autowired
	TransportPlanMapper    transportPlanMapper;
	
	@BeforeEach
	public void clearDB() {
		sectionRepository.deleteAll();
		transportPlanRepository.deleteAll();
		milestoneRepository.deleteAll();
		addressRepository.deleteAll();	
		
		insertTestData();
	}
	
	public void insertTestData() {
		
		Address addressS1 = new Address("HU", "Budapest", "Orczi Tér",   1234, 8, 45.12, 90.24);
		Address addressS2 = new Address("HU", "Budapest", "Blaha Tér",   2345, 10, 40.00, 90.00);
		Address addressS3 = new Address("HU", "Budapest", "Oktogon Tér", 3456, 10, 40.00, 90.00);
		Address addressD  = new Address("HU", "HUN", "HUN Tér", 0000, 00, 00.00, 00.00);
		
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
	
		TransportPlan tplan = new TransportPlan(30000, sections);
			 transportPlanRepository.save(tplan);
			 
		sections.forEach(it->it.setTransportPlan(tplan));
			sections = sectionRepository.saveAll(sections);

	}
	
/* ------------------------------- TEST CASES -------------------------------*/
	@Test
	void delayRegster()throws Exception{
		List<Address>       allAddress   = addressRepository      .findAll();
		List<Milestone>     allMilestone = milestoneRepository    .findAll();
		List<Section>       allSection   = sectionRepository      .findAll();
		List<TransportPlan> allTransPlan = transportPlanRepository.findAll();
		
		long   planID  = allTransPlan.get(0).getId();
		long   stoneID = allMilestone.get(0).getId();
		int    delay   = 2;
		double income  = allTransPlan.get(0).getInCome();
		
		LocalDateTime date = allMilestone.get(0).getPlannedTime();
		
		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isOk();
		
		Optional<Milestone>     updatedMilestone = milestoneRepository.findById(stoneID);
		Optional<TransportPlan> updatedPlan      = transportPlanRepository.findById(planID);
	
		assertThat(updatedPlan.get().getInCome())
					.isNotEqualTo(income);
		
		assertThat(updatedMilestone.get().getPlannedTime())
					.isNotEqualTo(date);
	}
	
	@Test
	void delayRegsterBadMileStoneIDResponse()throws Exception{
		
		List<TransportPlan> allTransPlan = transportPlanRepository.findAll();
		
		long   planID  = allTransPlan.get(0).getId();
		long   stoneID = -1;
		int    delay   = 2;
		double income  = allTransPlan.get(0).getInCome();
		

		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isBadRequest();
		
		Optional<TransportPlan> updatedPlan      = transportPlanRepository.findById(planID);
	
		assertThat(updatedPlan.get().getInCome())
					.isEqualTo(income);
	}
	
	@Test
	void delayRegsterBadTransportPlanIDResponse()throws Exception{
		
		List<Milestone>     allMilestone = milestoneRepository    .findAll();

		long   planID  = -1;
		long   stoneID = allMilestone.get(0).getId();
		int    delay   = 2;


		LocalDateTime date = allMilestone.get(0).getPlannedTime();
		
		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isNotFound();
		
		Optional<Milestone>     updatedMilestone = milestoneRepository.findById(stoneID);

		assertThat(updatedMilestone.get().getPlannedTime())
					.isEqualTo(date);
	}
	
	@Test
	void delayRegsterTestPercentIntervalls_MIN()throws Exception{

		List<Milestone>     allMilestone = milestoneRepository    .findAll();
		List<TransportPlan> allTransPlan = transportPlanRepository.findAll();
		
		long   planID  = allTransPlan.get(0).getId();
		long   stoneID = allMilestone.get(0).getId();
		int    delay   = (int) MIN_LOW_VALUE;
		double income  = allTransPlan.get(0).getInCome();
		
		LocalDateTime date = allMilestone.get(0).getPlannedTime();
		
		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isOk();
		
		Optional<Milestone>     updatedMilestone = milestoneRepository.findById(stoneID);
		Optional<TransportPlan> updatedPlan      = transportPlanRepository.findById(planID);
	
		assertThat(updatedPlan.get().getInCome())
					.isEqualTo(income*(1-MIN_LOW_PERCENT));
		
		assertThat(updatedMilestone.get().getPlannedTime())
					.isEqualTo(date.plusMinutes(delay));
	}
	
	@Test
	void delayRegsterTestPercentIntervalls_MED()throws Exception{

		List<Milestone>     allMilestone = milestoneRepository    .findAll();
		List<TransportPlan> allTransPlan = transportPlanRepository.findAll();
		
		long   planID  = allTransPlan.get(0).getId();
		long   stoneID = allMilestone.get(0).getId();
		int    delay   = (int) MIN_MED_VALUE;
		double income  = allTransPlan.get(0).getInCome();
		
		LocalDateTime date = allMilestone.get(0).getPlannedTime();
		
		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isOk();
		
		Optional<Milestone>     updatedMilestone = milestoneRepository.findById(stoneID);
		Optional<TransportPlan> updatedPlan      = transportPlanRepository.findById(planID);
	
		assertThat(updatedPlan.get().getInCome())
					.isEqualTo(income*(1-MIN_MED_PERCENT));
		
		assertThat(updatedMilestone.get().getPlannedTime())
					.isEqualTo(date.plusMinutes(delay));
	}
	
	@Test
	void delayRegsterTestPercentIntervalls_HIGH()throws Exception{

		List<Milestone>     allMilestone = milestoneRepository    .findAll();
		List<TransportPlan> allTransPlan = transportPlanRepository.findAll();
		
		long   planID  = allTransPlan.get(0).getId();
		long   stoneID = allMilestone.get(0).getId();
		int    delay   = (int) MIN_HIGH_VALUE;
		double income  = allTransPlan.get(0).getInCome();
		
		LocalDateTime date = allMilestone.get(0).getPlannedTime();
		
		WebTestClient
			.post()
			.uri(BASE_URI+planID+"/delay?stoneId="+stoneID+"&delay="+delay)
			.exchange()
			.expectStatus()
			.isOk();
		
		Optional<Milestone>     updatedMilestone = milestoneRepository.findById(stoneID);
		Optional<TransportPlan> updatedPlan      = transportPlanRepository.findById(planID);
	
		assertThat(updatedPlan.get().getInCome())
					.isEqualTo(income*(1-MIN_HIGH_PERCENT));
		
		assertThat(updatedMilestone.get().getPlannedTime())
					.isEqualTo(date.plusMinutes(delay));
	}
	
	
}
