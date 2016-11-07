package cooksys.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cooksys.component.Tweet;
import cooksys.entity.Credentials;
import cooksys.entity.TweetEntity;
import cooksys.entity.UserEntity;
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
		return tweetService.get();
	}
	
	@GetMapping("/{id}")
	public Tweet getTweet(@PathVariable Integer id) throws TweetControllerException {
		Tweet dbTweet = tweetService.findByIdAndActiveTrue(id);
		if(dbTweet == null)
			throw new TweetControllerException("Tweet not fouund!");
		return dbTweet;
	}

	@PostMapping
	public Tweet putTweet(@RequestBody TweetPostRequest tweetPostRequest ) throws TweetControllerException {
		Tweet tweet = tweetService.add(tweetPostRequest.content, tweetPostRequest.credentials);
		if (tweet == null)
			throw new TweetControllerException("Tweet cannot be added. Check credentials: username and password!"); 
		  return tweet;
//		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Tweet deleteTweet(@PathVariable Integer id, @RequestBody Credentials credentials) throws TweetControllerException {
		Tweet dbTweet = tweetService.delete(id, credentials);
		if (dbTweet == null)
			throw new TweetControllerException("Tweet cannot be deleted. Check tweet id and credentials: username and password!");
		return dbTweet;
	}
}

class TweetReturn {
	Long id;
	UserEntity author;
	Timestamp posted;
	String content;
	
	public TweetReturn (TweetEntity tweet){
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

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
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

@ResponseStatus
class TweetControllerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TweetControllerException(String msg) {
        super(msg);
    }
}

class TweetPostRequest {
		  public String content;
		  public Credentials credentials;
}