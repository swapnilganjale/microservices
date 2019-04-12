package com.project.userservice.reposervice;

import java.util.List;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserRoleDTO;
import com.project.userservice.exception.CustomException;
import com.project.userservice.model.User;

public interface UserRepoService {

	/**
	 * service will return user object from email
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * service will update the user record
	 * @param user
	 * @return
	 */
	User update(User user);

	/**
	 * service will get user record from user Id
	 * @param id
	 * @return
	 * @throws CustomException 
	 */
	User findById(Integer id) throws CustomException;
	
	/**
	 * save user record
	 * @param user
	 * @return {@link User}
	 * @throws CustomException 
	 */
	User saveUser(User user) throws CustomException;
	
	/**
	 * returns total no of users in the system
	 * @return
	 */
	Integer getTotalUsers();
	
	/**
	 * this will return the userdto object
	 * @param email
	 * @return
	 */
	UserDTO getUserDtoFromEmail(String email);

	/**
	 * delete user
	 * @param id
	 */
	void deleteUser(Integer id);

	List<UserDTO> findAllUsers() throws CustomException;
	

}