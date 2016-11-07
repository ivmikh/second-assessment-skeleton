package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cooksys.entity.Tweet;

public interface TweetRepo  extends JpaRepository<Tweet, Long> {
	Tweet findById(Long id);
	Tweet findByIdAndActiveTrue(Long id);
	List<Tweet> findAll();
	List<Tweet> findByActiveTrue();
////	  @Modifying
////	  @Transactional
//	  @Query("delete from User u where u.active = false")
//	  void deleteInactiveUsers();
//	select * from Tweet t order by id desc
	  @Query("from Tweet t where t.active = true order by timestamp desc")
	  List<Tweet> orderAllActive();
}
