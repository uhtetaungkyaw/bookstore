package com.jdc.bookstore.service;

import com.jdc.bookstore.entities.User;
import com.jdc.bookstore.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final UserRepository repository;

	public UserDetailServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = repository.findByUsername(username);
    	UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), true,
        		true, true, true, AuthorityUtils.createAuthorityList(curruser.getRole()));
    	return user;
    }
    
}