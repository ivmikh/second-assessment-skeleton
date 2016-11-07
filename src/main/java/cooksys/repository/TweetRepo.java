package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cooksys.entity.TweetEntity;

public interface TweetRepo  extends JpaRepository<TweetEntity, Long> {
	TweetEntity findById(Long id);
	TweetEntity findByIdAndActiveTrue(Long id);
	List<TweetEntity> findAll();
	List<TweetEntity> findByActiveTrue();
////	  @Modifying
////	  @Transactional
//	  @Query("delete from User u where u.active = false")
//	  void deleteInactiveUsers();
//	select * from Tweet t order by id desc
	  @Query("from TweetEntity t where t.active = true order by timestamp desc")
	  List<TweetEntity> orderAllActive();
}
