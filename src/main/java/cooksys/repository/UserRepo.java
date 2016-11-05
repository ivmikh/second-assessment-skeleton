package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.Credentials;
import cooksys.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
//	User findById(Long id);
	User findByUsername(String username);
	User findByCredentials(Credentials credentials);
	List<User> findAll();
}
