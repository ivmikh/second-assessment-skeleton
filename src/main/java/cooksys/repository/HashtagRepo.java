package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.Hashtag;
import cooksys.entity.Tweet;

public interface HashtagRepo extends JpaRepository<Hashtag, Long> {
	List<Hashtag> findAll();
}
