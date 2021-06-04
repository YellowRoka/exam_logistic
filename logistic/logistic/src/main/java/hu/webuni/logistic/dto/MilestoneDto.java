package hu.webuni.logistic.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.webuni.logistic.model.Address;

public class MilestoneDto {

	private long          id;
	private List<Address>       address= new ArrayList<>();
	private LocalDateTime plannedTime;
	
	public MilestoneDto() {};
	public MilestoneDto(long id, Address address, LocalDateTime plannedTime) {
		super();
		this.id = id;
		this.address.add(address);
		this.plannedTime = plannedTime;
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void addAddress(Address address) {
			this.address.add(address);
	}
	
	public void setAddress(List<Address> address) {
		if(this.address == null)
			this.address = address;
	}
	
	public Address getAddress(int idx) {
		return this.address.get(idx);
	}
	
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	
}