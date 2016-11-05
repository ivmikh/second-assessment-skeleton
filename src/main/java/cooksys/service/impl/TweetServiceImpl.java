package cooksys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.entity.Tweet;
import cooksys.repository.TweetRepo;
import cooksys.repository.UserRepo;
import cooksys.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{
 TweetRepo tweetRepo;
 UserRepo userRepo;
 
	public TweetServiceImpl(TweetRepo tweetRepo, UserRepo userRepo) {
		this.tweetRepo = tweetRepo;
		this.userRepo = userRepo;
	}

	@Override
	public List<Tweet> findAll() {
		return tweetRepo.findAll();
	}

	@Override
	public Tweet add(Tweet tweet) {
		System.out.println("Tweet User = " + userRepo.findByUsername(tweet.getCredentials().getUsername()) );
		tweet.setAuthor(userRepo.findByUsername(tweet.getCredentials().getUsername()));
		return tweetRepo.saveAndFlush(tweet);
//		return tweet;
	}
}