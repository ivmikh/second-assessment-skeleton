package cooksys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.component.Tweet;
import cooksys.entity.Credentials;
import cooksys.entity.TweetEntity;
import cooksys.entity.UserEntity;
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
	public Tweet findByIdAndActiveTrue(Integer id) {
		TweetEntity tweetEntity = tweetRepo.findByIdAndActiveTrue(id.longValue());
		return (tweetEntity == null) ? null : new Tweet(tweetEntity);
	}
	
	@Override
	public List<Tweet> get() {
		List<Tweet> listTweet = new ArrayList<>();
		for (TweetEntity tweetEntity: tweetRepo.orderAllActive()){
			listTweet.add( new Tweet(tweetEntity) );
		}
		return listTweet;
	}
	
	@Override
	public Tweet add(String content, Credentials credentials) {
		String tweetUsername =credentials.getUsername();
		UserEntity dbUser = userRepo.findByUsernameAndActiveTrue(tweetUsername); 
		if(dbUser == null)
			return null;
		Credentials dbCredentials = dbUser.getCredentials();
		if ( ! dbCredentials.equals(credentials) )
			return null;;
		return new Tweet(tweetRepo.saveAndFlush(new TweetEntity(dbUser, content)));

	}
	
	@Override
	public Tweet delete(Integer id, Credentials credentials) {
		Long longId = id.longValue();
		TweetEntity tweetEntity = tweetRepo.findById(longId); 
		if (tweetEntity == null || !credentials.equals(tweetEntity.getAuthor().getCredentials()))
			return null;
		Tweet tweet = new Tweet(tweetEntity);
		tweetEntity.setActive(false);
		tweetEntity.setContent(""); // Really delete content (optionally).
		tweetRepo.saveAndFlush(tweetEntity);
		return tweet;
	}
}