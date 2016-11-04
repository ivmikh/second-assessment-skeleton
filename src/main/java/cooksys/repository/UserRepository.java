package cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
//	User findById(Long id);
	User findByUserName(String userName);
}
