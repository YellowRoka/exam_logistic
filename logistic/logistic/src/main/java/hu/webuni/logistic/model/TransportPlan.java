package hu.webuni.logistic.model;

import java.util.List;

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
	private int           inCome;
	
	@OneToMany(mappedBy= "transportPlan")
	private List<Section> sections;
	
	
	public TransportPlan() {}
	public TransportPlan(long id, int inCome, List<Section> sections) {
		//super();
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

	public void setInCome(int inCome) {
		this.inCome = inCome;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	};
	
	
}
