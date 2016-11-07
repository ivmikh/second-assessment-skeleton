package cooksys.component;

import java.sql.Timestamp;

import cooksys.entity.UserEntity;

public class User {
	String username;
	Profile profile;
	Timestamp joined; // long
 public User(UserEntity user) {
	 this.username = user.getUsername();
	 this.profile = new Profile( user.getProfile() );
	 this.joined = user.getJoined(); //.getTime();
 }
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Profile getProfile() {
	return profile;
}
public void setProfile(Profile profile) {
	this.profile = profile;
}
public Timestamp getJoined() {
	return joined;
}
public void setJoined(Timestamp joined) {
	this.joined = joined;
}
 
 
}