package cooksys.service;

import java.util.List;

import cooksys.entity.Tweet;

public interface TweetService {
	List<Tweet> findAll();
	
	public Tweet add(Tweet tweet);
}
