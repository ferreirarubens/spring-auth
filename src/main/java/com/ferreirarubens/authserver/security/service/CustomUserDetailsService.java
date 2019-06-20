package com.ferreirarubens.authserver.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ferreirarubens.authserver.dao.IUserRepository;
import com.ferreirarubens.authserver.model.User;
import com.ferreirarubens.authserver.security.model.LoggedUser;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLoginAndActiveTrueAndProfileActiveTrue(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " Not found");
		}
		
		return new LoggedUser(user);
	}

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
}