package cooksys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cooksys.entity.Credentials;

public interface CredentialsRepo  extends JpaRepository<Credentials, Long> {
	Credentials findById(Long id);
	Credentials findByUsername(String username);
	List<Credentials> findAll();

}
