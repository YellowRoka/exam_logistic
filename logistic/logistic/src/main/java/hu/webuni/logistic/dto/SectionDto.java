package hu.webuni.logistic.dto;

import hu.webuni.logistic.model.Milestone;
import hu.webuni.logistic.model.TransportPlan;

public class SectionDto {

	private long          id;
	private Milestone     fromMilestone;
	private Milestone     toMilestone;
	private int           number = -1;
	private TransportPlan  transportPlan;
	
	
	public SectionDto() {};
	public SectionDto(long id, Milestone fromMilestone, Milestone toMilestone, int number) {
		super();
		this.id = id;
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

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
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
			this.transportPlan = transportPlan;
	}
	
	
}
