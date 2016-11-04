package cooksys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile {

	@Id
	@GeneratedValue
	private Long profileId;
	
	private String firstName;  // optional
	private String lastName;   // optional
	
	private String email;      // required !!!!!!!
	private String phone;      // optional
	
	
	
	public Long getId() {
		return profileId;
	}
	public void setId(Long id) {
		this.profileId = id;
	}
	
	public String getFirstName() {
		return (firstName == null)? "": firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return (lastName == null)? "": lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return (phone == null)? "": phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
