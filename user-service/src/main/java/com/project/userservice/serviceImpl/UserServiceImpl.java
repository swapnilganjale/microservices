package com.project.userservice.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.userservice.dto.ChangePasswordDTO;
import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserInfoDTO;
import com.project.userservice.dto.UserRoleDTO;
import com.project.userservice.exception.CustomException;
import com.project.userservice.exception.CustomExceptionCreatorService;
import com.project.userservice.model.User;
import com.project.userservice.orikamapper.OrikaMapper;
import com.project.userservice.page.dto.PageDTO;
import com.project.userservice.page.dto.PagingAndSortingRequest;
import com.project.userservice.reposervice.UserRepoService;
import com.project.userservice.repository.opt.OptionalParameters;
import com.project.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepoService userRepoService;

	@Autowired
	private OrikaMapper orikamapper;

	@Autowired
	private CustomExceptionCreatorService customExceptionCreatorService;

	
    @Autowired private PasswordEncoder passwordEncoder;
	 

	@Autowired
	private OptionalParameters<User> optionalParamters;

	@Override
	public User getLoggedInUser() throws CustomException {
		return null;
		/*
		 * 
		 * User user = null;
		 * 
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * 
		 * if (authentication == null) {
		 * 
		 * throw customExceptionCreatorService.getCustomException("0002"); } else {
		 * 
		 * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		 * 
		 * user = userRepoService.findByEmail(userDetails.getUsername());
		 * 
		 * }
		 * 
		 * return user;
		 */}

	@Override
	public UserDTO findByEmail(String username) throws CustomException {

		User user = userRepoService.findByEmail(username);

		if (user != null) {
			return orikamapper.map(user, UserDTO.class);
		} else {
			throw customExceptionCreatorService.getCustomException("0001");
		}

	}

	@Override
	public UserDTO saveUser(UserDTO userDto) throws CustomException {
 
		System.out.println("password == "+userDto.getPassword());
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

		User user = userRepoService.saveUser(orikamapper.map(userDto, User.class));

		return orikamapper.map(user, UserDTO.class);

	}

	@Override
	public UserDTO getUserByEmail(String emailId) {

		return orikamapper.map(userRepoService.findByEmail(emailId), UserDTO.class);

	}

	@Override
	public UserInfoDTO getUserProfileInfo() throws CustomException {
		return orikamapper.map(getLoggedInUser(), UserInfoDTO.class);
	}

	@Override
	public void updateUserInfo(UserInfoDTO userInfo) throws CustomException, IOException {

		User user = getLoggedInUser();

		if (!(Objects.isNull(userInfo.getProfileImage()))) {

			user.setProfileImage(userInfo.getProfileImage());
		}

		user.setDob(userInfo.getDob());
		user.setEmail(userInfo.getEmail());
		user.setGender(userInfo.getGender());
		user.setMobile(userInfo.getMobile());
		user.setFullName(userInfo.getFullName());

		userRepoService.saveUser(user);

	}

	@Override
	public void changePasswordOfUser(ChangePasswordDTO changePasswordDto) throws CustomException {
		/*
		 * 
		 * User user = getLoggedInUser();
		 * 
		 * String exsitingPassword = new
		 * String(decoder.decode(changePasswordDto.getExistingPassword()));
		 * 
		 * String newPassword = new
		 * String(decoder.decode(changePasswordDto.getNewPassword()));
		 * 
		 * if (passwordEncoder.matches(exsitingPassword, user.getPassword())) {
		 * 
		 * user.setPassword(passwordEncoder.encode(newPassword));
		 * 
		 * userRepoService.saveUser(user);
		 * 
		 * } else { throw customExceptionCreatorService.getCustomException("000113"); }
		 * 
		 */}

	@Override
	public String forgetPassword(String email) throws CustomException {

		User user = userRepoService.findByEmail(email);

		if (Objects.isNull(user)) {
			throw customExceptionCreatorService.getCustomException("0001");
		} else {
			// TODO logic to send email
			return "mail is sent to the emilId " + email;
		}

	}

	@Override
	public UserRoleDTO getUserInfo(Integer id) throws CustomException {

		try {
			return orikamapper.map(userRepoService.findById(id), UserRoleDTO.class);
		} catch (Exception e) {
			throw customExceptionCreatorService.getCustomException("0001");

		}
	}

	@Override
	public PageDTO<UserDTO> findWithOptionalParameters(PagingAndSortingRequest pageRequest) {

		Page<User> page = (Page<User>) optionalParamters.findWithOptionalParameters(pageRequest, User.class);

		List<UserDTO> userList = new ArrayList<>();

		page.getContent().stream().filter(Objects::nonNull).forEach(userObj -> {
			UserDTO userDto = new UserDTO();
			userDto.setFullName(userObj.getFullName());
			userDto.setEmail(userObj.getEmail());
			userDto.setGender(userObj.getGender());
			userDto.setMobile(userObj.getMobile());
			userDto.setDob(userObj.getDob());
			userList.add(userDto);
		});

		return new PageDTO<>(userList, page.getNumber(), page.getNumberOfElements(), page.getSize(),
				page.getTotalElements(), page.getTotalPages());

	}

	@Override
	public String updateUser(UserDTO userDto) throws CustomException, JsonProcessingException {

		User user = userRepoService.findByEmail(userDto.getEmail());

		if (Objects.isNull(user)) {
			throw customExceptionCreatorService.getCustomException("0001");
		} else {

			user.setFullName(userDto.getFullName());
			user.setGender(userDto.getGender());
			user.setMobile(userDto.getMobile());
			user.setDob(userDto.getDob());
			User updatedUser = userRepoService.saveUser(user);

			return "user saved successfully";

		}

	}

	@Override
	public void deleteUser(Integer id) throws JsonProcessingException, CustomException {

		userRepoService.deleteUser(id);

	}

	@Override
	public List<UserDTO> getAllUsers() throws CustomException {
		return userRepoService.findAllUsers();
	}

}