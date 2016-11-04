package cooksys.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
public class User {
	
	@Id
	@GeneratedValue
//	@Column(columnDefinition = "serial")  //it has "bigint" type by default
	private Long id;
	
	private String userName;
	
//	@OneToOne(fetch=FetchType.LAZY) // @MapsId
//	@JoinColumn(name="profile_id")
	@OneToOne @MapsId
	private Profile profile;
	
	@Column(insertable=true,updatable=false)
	private Timestamp joined;

//	public Long getId() {
//		return id;
//	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
