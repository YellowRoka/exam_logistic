package hu.webuni.logistic.dto;

import java.time.LocalDateTime;

import hu.webuni.logistic.model.Address;

public class MilestoneDto {

	private long          id;
	private Address       address;
	private LocalDateTime plannedTime;
	
	public MilestoneDto() {};
	public MilestoneDto(long id, Address address, LocalDateTime plannedTime) {
		super();
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