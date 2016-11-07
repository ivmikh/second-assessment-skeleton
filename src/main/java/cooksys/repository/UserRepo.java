package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.Credentials;
import cooksys.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
//	User findById(Long id);
	UserEntity findByUsername(String username);
	UserEntity findByUsernameAndActiveTrue(String username);
	UserEntity findByCredentials(Credentials credentials);
	UserEntity findByCredentialsAndActiveTrue(Credentials credentials);
	List<UserEntity> findAll();
	List<UserEntity> findByActiveTrue();
}
