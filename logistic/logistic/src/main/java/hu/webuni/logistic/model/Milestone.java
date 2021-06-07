package hu.webuni.logistic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "milestone")
public class Milestone {

	@Id
	@GeneratedValue
	private long          id;
	
	@OneToMany(mappedBy = "milestone")
	private List<Address>       address = new ArrayList<>();
	
	@OneToOne(mappedBy = "fromMilestone")
	private Section 	  sectionFrom;
	
	@OneToOne(mappedBy = "toMilestone")
	private Section 	  sectionTo;
	
	private LocalDateTime plannedTime;
	
	public Milestone() {};
	public Milestone(/*long id,*/ Address address, LocalDateTime plannedTime) {
		//super();
		//this.id = id;
		this.address.add(address);
		this.plannedTime = plannedTime;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	//public Address getAddress() {
	//	return address;
	//}
	//public void setAddress(Address address) {
	//	this.address = address;
	//}
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public void addAddress(Address address) {
		address.addMilestone(this);
		if(this.address == null)
			this.address.add(address);
	}
	
	public void setAddress(List<Address> address) {
		if(this.address == null)
			this.address = address;
	}
	
	public Address getAddress(int idx) {
		return this.address.get(idx);
	}

/*	public void setSection4From(Section section) {
		section.setFromMilestone(this);
		if(this.section == null)
			this.section = section;
	}
	
	public void setSection4To(Section section) {
		section.setToMilestone(this);
		if(this.section == null)
			this.section = section;
	}
	
	public Section getSection() {
		return this.section;
	}

*/

}
