package cooksys.component;

import java.sql.Timestamp;

import cooksys.entity.TweetEntity;

public class Tweet {
	Integer id;
	User author;
	Timestamp posted;
	String content; // optional
//	Tweet inReplyTo; // optional
//	Tweet repostOf; // optional
	
	public Tweet(TweetEntity tweetEntity){
		this.id = tweetEntity.getId().intValue();
		this.author = new User(tweetEntity.getAuthor());
		this.posted = tweetEntity.getTimestamp();
		this.content = tweetEntity.getContent();
//		this.inReplyTo = tweetEntity.
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Timestamp getPosted() {
		return posted;
	}

	public void setPosted(Timestamp posted) {
		this.posted = posted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}