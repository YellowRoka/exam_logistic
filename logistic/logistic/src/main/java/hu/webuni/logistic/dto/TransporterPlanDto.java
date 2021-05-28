package hu.webuni.logistic.dto;

import java.util.List;

import hu.webuni.logistic.model.Section;

public class TransporterPlanDto {

	private long          id;
	private int           inCome;
	private List<Section> sections;
	
	
	public TransporterPlanDto() {}

	public TransporterPlanDto(long id, int inCome, List<Section> sections) {
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

	public int getInCome() {
		return inCome;
	}
	
	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	};


}