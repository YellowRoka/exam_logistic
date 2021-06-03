package hu.webuni.logistic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistic.model.Milestone;
import hu.webuni.logistic.model.Section;
import hu.webuni.logistic.model.TransportPlan;
import hu.webuni.logistic.repository.MilestoneRepository;
import hu.webuni.logistic.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	public TransportPlanService(TransportPlanRepository transportPlanRepository,
			MilestoneRepository milestoneRepository) {
		this.transportPlanRepository = transportPlanRepository;
		this.milestoneRepository = milestoneRepository;
	}

	@Autowired
	TransportPlanRepository transportPlanRepository;
	
	@Autowired
	MilestoneRepository milestoneRepository;
	
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
			
	@Transactional
	public TransportPlan deleyRegister(long id, long stoneId, int delay) {
		TransportPlan transPlan = transportPlanRepository
									.findById(id)
									.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		int     idx        = 0;
		boolean fromFinded = false;
		boolean toFinded   = false;
		double  income     = 0.0;
		
		Milestone fromMilestone = null;
		Milestone toMilestone   = null;
		

		List<Section> sections = transPlan.getSections();
		for(idx = 0; idx <sections.size() ; idx++) {
			
			if(sections.get(idx).getFromMilestone().getId() == stoneId) {
				fromFinded = true;
				break;
			}
			
			if(sections.get(idx).getToMilestone().getId() == stoneId) {
				toFinded = true;
				break;
			}
		}
		
		if (fromFinded == false && toFinded == false){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		
		if(fromFinded) {
			fromMilestone = sections.get(idx).getFromMilestone();
			fromMilestone.setPlannedTime(fromMilestone.getPlannedTime().plusMinutes(delay));
			
			toMilestone = sections.get(idx).getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay));
		}
		
		if(toFinded) {
			toMilestone = sections.get(idx).getToMilestone();
			toMilestone.setPlannedTime(toMilestone.getPlannedTime().plusMinutes(delay));
			
			fromMilestone = sections.get(idx+1).getFromMilestone();
			fromMilestone.setPlannedTime(fromMilestone.getPlannedTime().plusMinutes(delay));	
		}
		
		milestoneRepository.save(fromMilestone);
		milestoneRepository.save(toMilestone);
		
		income = transPlan.getInCome();
		
		
		if(MIN_LOW_VALUE>=delay && MIN_MED_VALUE>delay) {
			income = (1-MIN_LOW_PERCENT)*income;
		}
		else if(MIN_MED_VALUE<=delay && MIN_HIGH_VALUE>delay) {
			income = (1-MIN_MED_PERCENT)*income;		
		}
		else if(MIN_HIGH_VALUE<=delay) {
			income = (1-MIN_HIGH_PERCENT)*income;
		}
		transPlan.setInCome(income);
		
		return transportPlanRepository.save(transPlan);
	}

	
}
