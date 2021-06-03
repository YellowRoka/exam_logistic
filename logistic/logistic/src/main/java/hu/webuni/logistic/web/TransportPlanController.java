package hu.webuni.logistic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.logistic.dto.TransportPlanDto;
import hu.webuni.logistic.mapper.TransportPlanMapper;
import hu.webuni.logistic.model.TransportPlan;
import hu.webuni.logistic.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	@Autowired
	TransportPlanService transportPlanService;
	
	@Autowired
	TransportPlanMapper transportPlanMapper;

	
	@PostMapping("/{id}/delay")
	public TransportPlanDto delayRegster(
			@PathVariable long id,
			@RequestParam(value="stoneId") long stoneId,
			@RequestParam(value="delay") int delay) {
		
		TransportPlan transPlan = transportPlanService.deleyRegister(id,stoneId,delay);
		
		
		TransportPlanDto transPlanDto = transportPlanMapper.modelToDto(transPlan);
		transPlanDto.getSections().forEach(it->it.setTransportPlan(null));
		
		return transPlanDto;
	}
}
