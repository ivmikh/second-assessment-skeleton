package cooksys.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.component.TweetPost;
import cooksys.entity.Credentials;
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
	public Tweet putTweet(@RequestBody Tweet tweet ) {
		System.out.println("Tweet is creating!!!!!*****************!!!!" );
//		if(!userService.exists(user)){
//		Tweet tweet = tweetService.getTweet(postTweet);
		  tweetService.add(tweet);
//		  return new TweetReturn(tweet);
		  return tweet;
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
}
//{ // Tweet
//	  id: 'integer'
//	  author: 'User',
//	  posted: 'timestamp',
//	  content?: 'string',
//	  inReplyTo?: 'Tweet',
//	  repostOf?: 'Tweet'
//	}