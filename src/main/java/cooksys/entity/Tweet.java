package cooksys.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

//import java.sql.Timestamp;
//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity
public class Tweet {

	@Id
	@GeneratedValue
	private Long id;
	private boolean active;
	@Transient
	private Credentials credentials;

	@ManyToOne
	private User author;
	
	@Column(insertable=true,updatable=false)
	private Timestamp timestamp;
	private String content;   // optional
//	private Tweet inReplyTo;  // optional
//	private Tweet repostOf;   // optional
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	@PrePersist
	final void timestamp() {
		this.timestamp = new Timestamp((new Date()).getTime());
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
//	public void setTimestamp(Timestamp timestamp) {
//		this.timestamp = timestamp;
//	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
//	public Tweet getInReplyTo() {
//		return inReplyTo;
//	}
//	public void setInReplyTo(Tweet inReplyTo) {
//		this.inReplyTo = inReplyTo;
//	}
//	public Tweet getRepostOf() {
//		return repostOf;
//	}
//	public void setRepostOf(Tweet repostOf) {
//		this.repostOf = repostOf;
//	}
//	
	
}
