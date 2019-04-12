package com.project.userservice.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.userservice.dto.ChangePasswordDTO;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserInfoDTO;
import com.project.userservice.dto.UserRoleDTO;
import com.project.userservice.exception.CustomException;
import com.project.userservice.page.dto.PageDTO;
import com.project.userservice.page.dto.PagingAndSortingRequest;
import com.project.userservice.service.UserService;

@CrossOrigin
@RestController
@RequestMapping({ "/users" })
public class UserController {

	@Autowired
	private UserService userService;
	

	/**
	 * this will give All users info
	 * 
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/getAllUsers")
	public List<UserDTO> getAllUsers() throws CustomException {
		return userService.getAllUsers();
	}
	
	@GetMapping("/getuser")
	public String getuser() {
		return  "Feign client request from User Service";
	}
	
	/**
	 * this will give users info by id
	 * 
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/{id}")
	public UserRoleDTO getUser(@PathVariable("id") String id) throws CustomException {
		return userService.getUserInfo(Integer.parseInt(id));
	}

	/**
	 * retrun all the uses with paginantion
	 * 
	 * @param pageRequest
	 * @return
	 */
	@PostMapping("/getAllUsers")
	public PageDTO<UserDTO> getUsers(@RequestBody PagingAndSortingRequest pageRequest) {

		return userService.findWithOptionalParameters(pageRequest);

	}

	/**
	 * api will update the user data
	 * 
	 * @param userDto
	 * @return
	 * @throws CustomException
	 * @throws JsonProcessingException
	 */
	@PutMapping("")
	public void updateUser(@RequestBody UserDTO userDto) throws CustomException, JsonProcessingException {
		userService.updateUser(userDto);
	}

	/**
	 * creates new user record
	 * 
	 * @param userDto
	 * @return
	 * @throws CustomException
	 * @throws JsonProcessingException
	 */
	@PostMapping("")
	public UserDTO addUser(@RequestBody UserDTO userDto) throws CustomException, JsonProcessingException {
		System.out.println(userDto.toString());
		userService.saveUser(userDto);
		return userDto;
	}

	/**
	 * used to hard delete usr record from admin panel
	 * 
	 * @param userDto
	 * @return
	 * @throws JsonProcessingException
	 * @throws CustomException
	 */
	@DeleteMapping("{id}")
	public UserDTO user(@PathVariable("id") String id) throws JsonProcessingException, CustomException {

		userService.deleteUser(Integer.parseInt(id));

		return null;
	}

	/**
	 * api used to get profile information of perticular user
	 * 
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/profile")
	public UserInfoDTO getUserProfileInfo() throws CustomException {
		return userService.getUserProfileInfo();
	}

	/**
	 * api used to update the information of profile of logged in user
	 * 
	 * @param userInfo
	 * @return
	 * @throws CustomException
	 * @throws IOException
	 */
	@PostMapping("/update")
	public String updateUserProfile(@RequestBody UserInfoDTO userInfo) throws CustomException, IOException {

		userService.updateUserInfo(userInfo);

		return "User Profile Updated Successfully";
	}

	/**
	 * this api is used to change the password of user
	 * 
	 * @param changePasswordDto
	 * @return
	 * @throws CustomException
	 */
	@PostMapping("/changepassowrd")
	public String changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDto) throws CustomException {

		System.out.println("old password == " + changePasswordDto.getExistingPassword());

		userService.changePasswordOfUser(changePasswordDto);

		return "password has been changed";

	}

	/**
	 * this api will send the email to registered user to change the password
	 * 
	 * @param email
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/forgetpassword/{id}")
	public String forgotPassword(@PathVariable("id") String email) throws CustomException {

		return userService.forgetPassword(email);
	}

}
