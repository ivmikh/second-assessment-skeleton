package cooksys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import cooksys.entity.Hashtag;
import cooksys.repository.HashtagRepo;
import cooksys.service.HashtagService;

@Service
public class HashtagServiceImpl implements HashtagService{
	
	HashtagRepo hashtagRepo;
	
	public HashtagServiceImpl(HashtagRepo hashtagRepo) {
		this.hashtagRepo = hashtagRepo;
	}
	
	@Override
	public List<Hashtag> parseAndPut(String content) {
		//To do
		return new ArrayList<Hashtag>();
	}
	
	private Set<String> parse(String content) {
		Set<String> hashtagsUnique = new HashSet<String>();
        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith("#")) {
                hashtagsUnique.add(token);
            }
        }
        return hashtagsUnique;
    }
	

}
