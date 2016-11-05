package cooksys.entity;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
public class User {
	
	@Id
	@GeneratedValue
//	@Column(columnDefinition = "serial")  //it has "bigint" type by default
	private Long id;
	
	private String username;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Credentials credentials;
	
	@OneToOne(cascade = CascadeType.ALL) //@MapsId
	private Profile profile;
	
	@Column(insertable=true,updatable=false)
	private Timestamp joined;

	@OneToMany
	private List<Tweet> tweets;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
//		this.username = username;
		this.username = getCredentials().getUsername();
	}
	
	private Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	  @PrePersist
	  final void joined() {
		this.joined = new Timestamp( (new Date()).getTime() );
	  }
	  
	  public long getJoined() {
		  return this.joined.getTime();
	  }
}
