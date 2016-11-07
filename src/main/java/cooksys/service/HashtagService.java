package cooksys.service;

import java.util.List;

import cooksys.entity.Hashtag;

public interface HashtagService {
	List<Hashtag> parseAndPut(String content);

}
