package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
//	User findById(Long id);
	User findByUsername(String username);
	List<User> findAll();
}
