package hu.webuni.logistic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.logistic.dto.SectionDto;
import hu.webuni.logistic.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapping {

	@Mapping(target = "transportPlan.sections", ignore = true)
	SectionDto modelToDto(Section section);
	
	Section dtoToModel(SectionDto sectionDto);
}
