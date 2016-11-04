package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	User findById(Long id);
	User findByUserName(String userName);
	List<User> findAll();
}
