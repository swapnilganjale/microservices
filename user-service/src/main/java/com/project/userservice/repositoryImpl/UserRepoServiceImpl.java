package com.project.userservice.repositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.dto.UserRoleDTO;
import com.project.userservice.exception.CustomException;
import com.project.userservice.exception.CustomExceptionCreatorService;
import com.project.userservice.model.User;
import com.project.userservice.orikamapper.OrikaMapper;
import com.project.userservice.reposervice.UserRepoService;
import com.project.userservice.repository.UserRepository;

@Service
public class UserRepoServiceImpl implements UserRepoService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrikaMapper orikaMapper;

	@Autowired
	private CustomExceptionCreatorService customException;

	@Override
	public User findByEmail(String email) {

		return userRepository.getByEmail(email);
	}

	@Override
	public User findById(Integer id) throws CustomException{

		return userRepository.findById(id).get();

	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User saveUser(User user) throws CustomException {

		try {

			return userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw customException.getCustomException("0001");
		}

	}

	@Override
	public Integer getTotalUsers() {

		return userRepository.findAll().size();
	}

	@Override
	public UserDTO getUserDtoFromEmail(String email) {
		UserDTO userDto = null;

		try {
			User user = findByEmail(email);
			userDto = orikaMapper.map(user, UserDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDto;
	}

	@Override
	public void deleteUser(Integer id) {

		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<UserDTO> findAllUsers() throws CustomException{
 		return orikaMapper.mapAsList(userRepository.findAll(), UserDTO.class);
	}

}