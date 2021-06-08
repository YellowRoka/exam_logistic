package hu.webuni.logistic.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.logistic.model.Milestone;

public class AddressDto {

	private long   id;
	
	@NotEmpty(message = "Set a code from 2 letters")
	@Length(min = 2, max = 2, message="code most be setted from 2 letter")
	private String codeISO; //max lenght 2
	
	private String city;
	private String street;
	private int    zipCode;
	private int    hauseNumber;
	private double horizontalCircle; //szélességi //latitude
	private double verticalCircle;   //hosszúsági  //longitude
	
	private MilestoneDto milestone;

	
	public AddressDto(){}
	public AddressDto(long id, String codeISO, String city, String street, int zipCode, int hauseNumber,
			double horizontalCircle, double verticalCircle) {
		super();
		this.id = id;
		this.codeISO = codeISO;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
		this.hauseNumber = hauseNumber;
		this.horizontalCircle = horizontalCircle;
		this.verticalCircle = verticalCircle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeISO() {
		return codeISO;
	}

	public void setCodeISO(String codeISO){
		if( (codeISO.length() == 2)  &&
			(!codeISO.contains("0")) &&
			(!codeISO.contains("1")) &&
			(!codeISO.contains("2")) &&
			(!codeISO.contains("3")) &&
			(!codeISO.contains("4")) &&
			(!codeISO.contains("5")) &&
			(!codeISO.contains("6")) &&
			(!codeISO.contains("7")) &&
			(!codeISO.contains("8")) &&
			(!codeISO.contains("9"))
		){
				this.codeISO = codeISO;
			}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getHauseNumber() {
		return hauseNumber;
	}

	public void setHauseNumber(int hauseNumber) {
		this.hauseNumber = hauseNumber;
	}

	public double getHorizontalCircle() {
		return horizontalCircle;
	}

	public void setHorizontalCircle(double horizontalCircle) {
		this.horizontalCircle = horizontalCircle;
	}

	public double getVerticalCircle() {
		return verticalCircle;
	}

	public void setVerticalCircle(double verticalCircle) {
		this.verticalCircle = verticalCircle;
	};

	//public void setMilestone(List<Milestone> milestone) {
	//	this.milestone = milestone;
	//}
	
	public void addMilestone(MilestoneDto milestone) {
		this.milestone = milestone;
	}
	
	public MilestoneDto getMilestone() {
		return this.milestone;
	}
	
	//public Milestone getMilestone(int idx) {
	//	return this.milestone.get(idx);
	//}
}
