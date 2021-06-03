package hu.webuni.logistic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.logistic.dto.TransportPlanDto;
import hu.webuni.logistic.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	@Mapping(target = "section.transportPlan", ignore = true)
	public TransportPlanDto modelToDto(TransportPlan transportPlan);
}
