package cooksys.service;

import java.util.List;

import cooksys.component.TweetObj;
import cooksys.entity.Credentials;

public interface TweetService {
	TweetObj findByIdAndActiveTrue(Integer id);
	
	List<TweetObj> get();
	
	public TweetObj add(String content, Credentials credentials);
	public TweetObj delete(Integer id, Credentials credentials);
}
