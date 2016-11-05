package cooksys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.entity.Credentials;
import cooksys.entity.Tweet;
import cooksys.entity.User;
import cooksys.repository.CredentialsRepo;
import cooksys.repository.TweetRepo;
import cooksys.repository.UserRepo;
import cooksys.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{
 TweetRepo tweetRepo;
 UserRepo userRepo;
 CredentialsRepo credentialsRepo;
 
	public TweetServiceImpl(TweetRepo tweetRepo, UserRepo userRepo, CredentialsRepo credentialsRepo) {
		this.tweetRepo = tweetRepo;
		this.userRepo = userRepo;
		this.credentialsRepo = credentialsRepo;
	}

	@Override
	public List<Tweet> findAll() {
		return tweetRepo.findAll();
	}

	@Override
	public Tweet add(Tweet tweet) {
		Credentials tweetCredentials = tweet.getCredentials();
		String tweetUsername =tweetCredentials.getUsername();
		User dbUser = userRepo.findByUsername(tweetUsername); 
		Credentials dbCredentials = dbUser.getCredentials();
//		User dbUser = userRepo.findByCredentials(tweetCredentials);  // cannot do this for credentials with unknown id ?
		System.out.println("Found User with password " + dbCredentials.getPassword() + " !!!!!!!!!**********!!!!!!!!!!!!  ? " + tweetCredentials.getPassword() );
		if (dbCredentials.getPassword().equals(tweetCredentials.getPassword() ) ) {
			tweet.setCredentials(dbCredentials); // to set tweet credentials id (credentials may be redundant in tweet !).
			tweet.setAuthor(dbUser);
			return tweetRepo.saveAndFlush(tweet);
		} else {
		    return tweet;
		}
	}
}