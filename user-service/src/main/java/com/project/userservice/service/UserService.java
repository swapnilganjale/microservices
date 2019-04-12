package com.project.userservice.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.userservice.dto.ChangePasswordDTO;
import com.project.userservice.dto.SaveUserDto;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserInfoDTO;
import com.project.userservice.dto.UserRoleDTO;
import com.project.userservice.exception.CustomException;
import com.project.userservice.model.User;
import com.project.userservice.page.dto.PageDTO;
import com.project.userservice.page.dto.PagingAndSortingRequest;


public interface UserService {

	/**
	 * this service will return the User details of logged in user OR
	 * user who is requesting using token
	 * @return {@link User}
	 * @throws CustomException
	 */
	User getLoggedInUser() throws CustomException;
 
	
	/**
	 * this will return user record from email
	 * @param username
	 * @return {@link UserDTO}
	 * @throws CustomException 
	 */

	UserDTO findByEmail(String username) throws CustomException;
	
	/**
	 * THis will save the user record to the database
	 * @param user
	 * @throws CustomException 
	 */
	UserDTO saveUser(UserDTO user) throws CustomException;
	
	/**
	 * this method will return the user object
	 * @param emailId
	 * @return
	 */
	UserDTO getUserByEmail(String emailId);
	

	/**
	 * method will return the information of user which is logged in
	 * @return
	 * @throws CustomException 
	 */
	UserInfoDTO getUserProfileInfo() throws CustomException;

	/**
	 * It will update user record
	 * @param userInfo
	 * @throws CustomException 
	 * @throws IOException 
	 */
	void updateUserInfo(UserInfoDTO userInfo) throws CustomException, IOException;

	/**
	 * method will change the password for logged in user
	 * @param changePasswordDto
	 * @throws CustomException 
	 */
	void changePasswordOfUser(ChangePasswordDTO changePasswordDto) throws CustomException;
	
	/**
	 * method will send the forgot password link to registered email id
	 * @param email
	 * @throws CustomException 
	 */
	String forgetPassword(String email) throws CustomException;
	
	
	/**
	 * This api will give use dto
	 * @return
	 * @throws CustomException 
	 */
	UserRoleDTO getUserInfo(Integer id) throws CustomException;

	/**
	 * pagination for users
	 * @return
	 */
	
	PageDTO<UserDTO> findWithOptionalParameters(PagingAndSortingRequest pageRequest);


	/**
	 * service will update the user from the database
	 * @param userDto
	 * @throws CustomException 
	 * @throws JsonProcessingException 
	 */

	String updateUser(UserDTO userDto) throws CustomException, JsonProcessingException;
	
	/**
	 * deletes user
	 * @param i
	 * @throws CustomException 
	 * @throws JsonProcessingException 
	 */
	void deleteUser(Integer id) throws JsonProcessingException, CustomException;


	List<UserDTO> getAllUsers() throws CustomException;
	
}