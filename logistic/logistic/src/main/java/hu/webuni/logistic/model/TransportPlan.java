package hu.webuni.logistic.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

@NamedEntityGraph(name = "TransportPlan", attributeNodes = @NamedAttributeNode("sections"))
@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private long          id;
	private double        inCome;
	
	@OneToMany(mappedBy= "transportPlan")
	private List<Section> sections;
	
	
	public TransportPlan() {}
	public TransportPlan(/*long id,*/ double inCome, List<Section> sections) {
		//super();
		//this.id = id;
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		
		sections.stream().forEach(it -> it.setTransportPlan(this));
			
		if(this.sections != null)
			this.sections = sections;
	};
	
	public void addSections(Section section) {
		section.setTransportPlan(this);
		if(this.sections != null)
			this.sections.add(section);
	};
	
	
}
