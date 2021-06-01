package hu.webuni.logistic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "section")
public class Section {

	@Id
	@GeneratedValue
	private long      id;
	private int       number=-1;
	
	@OneToOne
	private Milestone fromMilestone;
	
	@OneToOne
	private Milestone toMilestone;

	@ManyToOne
	private TransportPlan transportPlan;
	
	
	public Section() {};
	public Section(/*long id,*/ Milestone fromMilestone, Milestone toMilestone, int number) {
		//super();
		//this.id = id;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
		this.number = number;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	//public void setFromMilestone(Milestone fromMilestone) {
	//	this.fromMilestone = fromMilestone;
	//}

	//public Milestone getToMilestone4From() {
	//	return fromMilestone;
	//}
	
	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		//toMilestone.setSection4To(this);
		if(this.toMilestone == null)
			this.toMilestone = toMilestone;
	}
	
	public void setFromMilestone(Milestone fromMilestone) {
		//fromMilestone.setSection4From(this);
		if(this.fromMilestone == null)
			this.fromMilestone = fromMilestone;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		//transportPlan.addSections(this);
		if(this.transportPlan == null)
			this.transportPlan = transportPlan;
	}
	
}
