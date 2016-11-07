package cooksys.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

@Entity
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	private String label;
	@Column(insertable=true,updatable=true)
	private Timestamp timestamp;
	
	@ManyToMany //(cascade = CascadeType.ALL)
//	@JoinTable
	private List<TweetEntity> tweets;

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
	
	@PreUpdate
	final void timestamp() {
		this.timestamp = new Timestamp((new Date()).getTime());
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public List<TweetEntity> getTweets() {
		return tweets;
	}

	public void setTweets(List<TweetEntity> tweets) {
		this.tweets = tweets;
	}
	
	
}
