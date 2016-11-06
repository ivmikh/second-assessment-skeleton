package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.Tweet;

public interface TweetRepo  extends JpaRepository<Tweet, Long>{
	Tweet findById(Long id);
	Tweet findByIdAndActiveTrue(Long id);
	List<Tweet> findAll();
	List<Tweet> findByActiveTrue();
}
