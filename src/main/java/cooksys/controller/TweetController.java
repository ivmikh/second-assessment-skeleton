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
//		return tweetService.findAll();
//		return tweetService.findByActiveTrue();
		return tweetService.get();
	}
	
	@GetMapping("/{id}")
	public Tweet getTweet(@PathVariable Integer id) throws TweetControllerException {
//		System.out.println("User ID is received!!!!!*****************!!!!");
//		return tweetService.findAll();
		Tweet dbTweet = tweetService.findByIdAndActiveTrue(id);
		if(dbTweet == null)
			throw new TweetControllerException("Tweet not fouund!");
		return dbTweet;
	}

	@PostMapping
	public Tweet putTweet(@RequestBody Tweet tweet ) {
		System.out.println("Tweet is creating!!!!!*****************!!!!" );
		  
		  TweetReturn tweetReturn = new TweetReturn(tweetService.add(tweet));
//		  return tweetReturn;
		  return tweet;
//		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Tweet deleteUser(@PathVariable Integer id, @RequestBody Credentials credentials) throws TweetControllerException {
		Tweet dbTweet = tweetService.delete(id, credentials);
		if (dbTweet == null)
			throw new TweetControllerException("Tweet cannot be deleted. Check tweet id and credentials: username and password!");
		return dbTweet;
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
