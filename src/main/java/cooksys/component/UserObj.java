package cooksys.component;

import java.sql.Timestamp;

import cooksys.entity.User;

public class UserObj {
	String username;
	ProfileObj profile;
	Timestamp joined; // long
 public UserObj(User user) {
	 this.username = user.getUsername();
	 this.profile = new ProfileObj( user.getProfile() );
	 this.joined = user.getJoined(); //.getTime();
 }
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public ProfileObj getProfile() {
	return profile;
}
public void setProfile(ProfileObj profile) {
	this.profile = profile;
}
public Timestamp getJoined() {
	return joined;
}
public void setJoined(Timestamp joined) {
	this.joined = joined;
}
 
 
}