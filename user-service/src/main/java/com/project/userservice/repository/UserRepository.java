package com.project.userservice.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.userservice.model.User;

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
	
	@Transactional
	@Modifying
	@Query("delete from User u where u.email =:email ")
	void deleteUserByEmail(@Param("email")String email);
	
	
}