package com.api.parkingcontrol.configs.security;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	};

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return new User(userModel.getUsername(), userModel.getPassword(),true,true,true,true, userModel.getAuthorities());
	}
	
	@Transactional
	public UserModel save(UserModel userModel) {
		return userRepository.save(userModel);	
	};

	public Optional<UserModel> findByUsername(String username) {
		return userRepository.findByUsername(username);
		
	}

	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserModel> findById(UUID id) {
		return userRepository.findById(id);
	}
	
	@Transactional
	public void delete(UserModel userModel) {
		userRepository.delete(userModel);	
	};
	
	

}
