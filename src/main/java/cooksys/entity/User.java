package cooksys.entity;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
public class User {
	
	@Id
	@GeneratedValue
//	@Column(columnDefinition = "serial")
	private Long id;
	
	private String userName;
	
//	@Generated(GenerationTime.INSERT)
	@Column(insertable=true,updatable=false)
//	private long timeStamp;
	private Timestamp joined;
//	private long joined;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	  @PrePersist
	  void joined() {
//	    this.joined = (new Date()).getTime();
		this.joined = new Timestamp( (new Date()).getTime() );
	  }
	  
	  public long getJoined() {
		  return this.joined.getTime();
	  }


}
