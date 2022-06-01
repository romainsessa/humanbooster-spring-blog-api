package com.hb.blogapi.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hb.blogapi.entities.InternalRole;
import com.hb.blogapi.entities.InternalUser;
import com.hb.blogapi.repositories.InternalUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private InternalUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		InternalUser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		User userDetails = new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRoles()));
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<InternalRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (InternalRole role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		return authorities;
	}

}
