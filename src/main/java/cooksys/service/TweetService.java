package cooksys.service;

import java.util.List;

import cooksys.component.Tweet;
import cooksys.entity.Credentials;

public interface TweetService {
	Tweet findByIdAndActiveTrue(Integer id);
	
	List<Tweet> get();
	
	public Tweet add(String content, Credentials credentials);
	public Tweet delete(Integer id, Credentials credentials);
}
