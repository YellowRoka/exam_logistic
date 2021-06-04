package hu.webuni.logistic.dto;

import java.util.List;

import hu.webuni.logistic.model.Section;

public class TransportPlanDto {

	private long          id;
	private double        inCome;
	private List<SectionDto> sections;
	
	
	public TransportPlanDto() {}

	public TransportPlanDto(long id, int inCome, List<SectionDto> sections) {
		super();
		this.id = id;
		this.inCome = inCome;
		this.sections = sections;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getInCome() {
		return inCome;
	}
	
	public void setInCome(double inCome) {
		this.inCome = inCome;
	}
	
	public SectionDto getSection(int idx) {
		return sections.get(idx);
	}
	
	public List<SectionDto> getSections() {
		return sections;
	}

	public void setSections(List<SectionDto> sections) {
		this.sections = sections;
	};
	
	public void addSection(SectionDto section) {
		this.sections.add(section);
	};



}