package com.project.authserver.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.authserver.repository.UserRepository;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("username ###############################::"+username);
		
		return userRepository.findByEmail(username)
				.map(u -> new User(u.getEmail(), u.getPassword(),
						AuthorityUtils.createAuthorityList(
								u.getRoles().stream().map(r -> "ROLE_" + r.getRoleName().toUpperCase())
										.collect(Collectors.toList()).toArray(new String[] {}))))
				.orElseThrow(() -> new UsernameNotFoundException(
						"No user with " + "the name " + username + "was found in the database"));
	}

}
