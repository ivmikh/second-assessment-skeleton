package cooksys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.component.TweetObj;
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
	public TweetObj findByIdAndActiveTrue(Integer id) {
		Tweet tweetEntity = tweetRepo.findByIdAndActiveTrue(id.longValue());
		return (tweetEntity == null) ? null : new TweetObj(tweetEntity);
	}
	
	@Override
	public List<TweetObj> get() {
		List<TweetObj> listTweet = new ArrayList<>();
		for (Tweet tweetEntity: tweetRepo.orderAllActive()){
			listTweet.add( new TweetObj(tweetEntity) );
		}
		return listTweet;
	}
	
	@Override
	public TweetObj add(String content, Credentials credentials) {
		String tweetUsername =credentials.getUsername();
		User dbUser = userRepo.findByUsernameAndActiveTrue(tweetUsername); 
		if(dbUser == null)
			return null;
		Credentials dbCredentials = dbUser.getCredentials();
		if ( ! dbCredentials.equals(credentials) )
			return null;;
		return new TweetObj(tweetRepo.saveAndFlush(new Tweet(dbUser, content)));

	}
	
	@Override
	public TweetObj delete(Integer id, Credentials credentials) {
		Long longId = id.longValue();
		Tweet tweetEntity = tweetRepo.findById(longId); 
		if (tweetEntity == null || !credentials.equals(tweetEntity.getAuthor().getCredentials()))
			return null;
		TweetObj tweet = new TweetObj(tweetEntity);
		tweetEntity.setActive(false);
		tweetEntity.setContent(""); // Really delete content (optionally).
		tweetRepo.saveAndFlush(tweetEntity);
		return tweet;
	}
}