package hu.webuni.logistic.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "milestone")
public class Milestone {

	@Id
	@GeneratedValue
	private long          id;
	
	@OneToOne
	private Address       address;
	
	@OneToOne
	Section section;
	
	private LocalDateTime plannedTime;
	
	public Milestone() {};
	public Milestone(long id, Address address, LocalDateTime plannedTime) {
		//super();
		this.id = id;
		this.address = address;
		this.plannedTime = plannedTime;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	
}
