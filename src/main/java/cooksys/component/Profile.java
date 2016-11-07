package cooksys.component;

import cooksys.entity.ProfileEntity;

public class Profile {
 String firstName;
 String lastName;
 String email;
 String phone;
 public Profile(ProfileEntity profileEntity) {
	 this.firstName = profileEntity.getFirstName();
	 this.lastName = profileEntity.getLastName();
	 this.email = profileEntity.getEmail();
	 this.phone = profileEntity.getPhone();
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
 
}
//{ // Profile
//	  firstName?: 'string',
//	  lastName?: 'string',
//	  email: 'string',
//	  phone?: 'string'
//	}