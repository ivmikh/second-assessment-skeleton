package cooksys.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.entity.Tweet;
import cooksys.entity.User;
import cooksys.service.TweetService;

@RestController
@RequestMapping("tweets")
public class TweetController {
	private TweetService tweetService;

	public TweetController(TweetService tweetService) {
		this.tweetService = tweetService;
	}
	
	@GetMapping
	public List<Tweet> getTweets() {
//		System.out.println("User ID is received!!!!!*****************!!!!");
		return tweetService.findAll();
	}

	@PostMapping
	public TweetReturn putTweet(@RequestBody Tweet tweet ) {
		System.out.println("Tweet is creating!!!!!*****************!!!!" );
		  
		  TweetReturn tweetReturn = new TweetReturn(tweetService.add(tweet));
		  return tweetReturn;
//		  return tweet;
//		}
	}
}

class TweetReturn {
	Long id;
	User author;
	Timestamp posted;
	String content;
	
	public TweetReturn (Tweet tweet){
		this.id = tweet.getId();
		this.author = tweet.getAuthor();
		this.posted = tweet.getTimestamp();
		this.content = tweet.getContent();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
//{ // Tweet
//	  id: 'integer'
//	  author: 'User',
//	  posted: 'timestamp',
//	  content?: 'string',
//	  inReplyTo?: 'Tweet',
//	  repostOf?: 'Tweet'
//	}