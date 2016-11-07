package cooksys.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	private String label;
	@Column(insertable=true,updatable=false)
	private Timestamp firstUsed;
	
	@Column(insertable=true,updatable=true)
	private Timestamp lastUsed;
	
	@ManyToMany //(cascade = CascadeType.ALL)
//	@JoinTable
	private List<Tweet> tweets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel (String label) {
		this.label = label;
	}
	
	@PrePersist
	final void firstUsed() {
		this.firstUsed = new Timestamp((new Date()).getTime());
	}
	public Timestamp getFirstUsed() {
		return firstUsed;
	}
	
	@PreUpdate
	final void lastUsed() {
		this.lastUsed = new Timestamp((new Date()).getTime());
	}
	public Timestamp getLastUsed() {
		return lastUsed;
	}
	
	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	
}
