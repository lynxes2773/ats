package example.hibernate;
import java.sql.Date;
import javax.validation.constraints.Size;
import javax.enterprise.inject.Default;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ats.util.RegexProvider;
import ats.web.validation.*;

@GroupSequence({Required.class, Length.class, Format.class, Candidate.class})
public class Candidate implements java.io.Serializable {
	
	private Integer candidateId;

	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=20, message="{candidate.name.size}", groups=Length.class)
	private String firstName;

	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=20, message="{candidate.name.size}", groups=Length.class)
	private String lastName;
	private java.sql.Date dob;

	private Character gender;

	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=7, max=50, message="{candidate.email.size}", groups=Length.class)
	@Email(message="{candidate.email.format}", groups=Format.class)
	private String email;

	private String phone;
	private String intlDialingCode;
	private String streetAddress1;
	private String streetAddress2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private Character status;
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

