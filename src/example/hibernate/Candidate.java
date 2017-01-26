package example.hibernate;
import java.sql.Date;
import javax.validation.constraints.Size;
import javax.enterprise.inject.Default;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ats.util.RegexProvider;
import ats.web.validation.*;

@Entity
@Table(name = "CANDIDATE")
@GroupSequence({Required.class, Length.class, Format.class, Candidate.class})
public class Candidate implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "candidate_id", updatable = false, nullable = false)
	private Integer candidateId;

	@Column(name = "first_name", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=20, message="{candidate.name.size}", groups=Length.class)
	private String firstName;

	@Column(name = "last_name", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=20, message="{candidate.name.size}", groups=Length.class)
	private String lastName;
	
	@Column(name = "date_of_birth", updatable = true, nullable = true)
	private java.sql.Date dob;

	@Column(name = "gender", updatable = true, nullable = false)
	private Character gender;

	@Column(name = "email", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=7, max=50, message="{candidate.email.size}", groups=Length.class)
	@Email(message="{candidate.email.format}", groups=Format.class)
	private String email;

	@Column(name = "phone", updatable = true, nullable = true)
	private String phone;

	@Column(name = "intl_dialing_code", updatable = true, nullable = true)
	private String intlDialingCode;

	@Column(name = "street_address_1", updatable = true, nullable = true)
	private String streetAddress1;

	@Column(name = "street_address_2", updatable = true, nullable = true)
	private String streetAddress2;

	@Column(name = "city", updatable = true, nullable = true)
	private String city;

	@Column(name = "state", updatable = true, nullable = true)
	private String state;

	@Column(name = "zipcode", updatable = true, nullable = true)
	private String zipCode;

	@Column(name = "country", updatable = true, nullable = true)
	private String country;

	@Column(name = "status", updatable = true, nullable = true)
	private Character status;

	@Column(name = "summary", updatable = true, nullable = true)
	private String summary;
	
	//empty constructor
	public Candidate()
	{}
	
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public java.sql.Date getDob() {
		return dob;
	}
	public void setDob(java.sql.Date dob) {
		this.dob = dob;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIntlDialingCode() {
		return intlDialingCode;
	}
	public void setIntlDialingCode(String intlDialingCode) {
		this.intlDialingCode = intlDialingCode;
	}
	public String getStreetAddress1() {
		return streetAddress1;
	}
	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.firstName);
		buffer.append(" ");
		buffer.append(this.lastName);
		return buffer.toString();
	}
}

