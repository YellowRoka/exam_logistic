package hu.webuni.logistic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue
	private long   id;
	private String codeISO; //max lenght 2
	private String city;
	private String street;
	private int    zipCode;
	private int    hauseNumber;
	private double horizontalCircle; //szélességi //latitude
	private double verticalCircle;   //hosszúsági  //longitude  
	
	//@ManyToOne//(mappedBy = "address")
	@OneToOne
	private Milestone milestone;
	
	public Address(){}
	public Address(/*long id,*/ String codeISO, String city, String street, int zipCode, int hauseNumber,
			double horizontalCircle, double verticalCircle) {
		//super();
		//this.id = id;
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

	public void setCodeISO(String codeISO) {
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
		//else {
		//	throw new Exception("allowed lenght: 2, use only letters"); 
		//}
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

	public void setMilestone(Milestone milestone) {
		milestone.setAddress(this);
		if(this.milestone == null)
			this.milestone = milestone;
	}
	
	public Milestone getMilestone() {
		return this.milestone;
	}
	
	
}
