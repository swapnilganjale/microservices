package com.project.authserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.authserver.entity.User;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * method return unique User object.THis method is used by UserDetailsService() Spring Security
	 * @param email
	 * @return 
	 */
	Optional<User> findByEmail(String email);

	/**
	 * method return unique object from emailid
	 * @param email
	 * @return {@link User}
	 */
	User getByEmail(String email);
	
	
}