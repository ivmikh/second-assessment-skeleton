package cooksys.service;

import java.util.List;

import cooksys.entity.Credentials;
import cooksys.entity.Tweet;

public interface TweetService {
	List<Tweet> findAll();
	List<Tweet> findByActiveTrue();
	Tweet findByIdAndActiveTrue(Integer id);
	
	List<Tweet> get();
	
	public Tweet add(Tweet tweet);
	public Tweet delete(Integer id, Credentials credentials);
}
