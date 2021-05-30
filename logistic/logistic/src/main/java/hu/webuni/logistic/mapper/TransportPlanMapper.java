package hu.webuni.logistic.mapper;

import org.mapstruct.Mapper;

import hu.webuni.logistic.dto.TransportPlanDto;
import hu.webuni.logistic.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	public TransportPlanDto modelToDto(TransportPlan transportPlan);
}
