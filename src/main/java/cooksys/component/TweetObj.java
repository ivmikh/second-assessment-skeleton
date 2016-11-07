package cooksys.component;

import java.sql.Timestamp;

import cooksys.entity.Tweet;

public class TweetObj {
	Integer id;
	UserObj author;
	Timestamp posted;
	String content; // optional
//	Tweet inReplyTo; // optional
//	Tweet repostOf; // optional
	
	public TweetObj(Tweet tweetEntity){
		this.id = tweetEntity.getId().intValue();
		this.author = new UserObj(tweetEntity.getAuthor());
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

	public UserObj getAuthor() {
		return author;
	}

	public void setAuthor(UserObj author) {
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