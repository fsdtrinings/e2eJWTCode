package com.ncs.abc.service;

import org.springframework.stereotype.Service;

import com.ncs.abc.model.AppUser;

import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public interface AppUserService extends UserDetailsService{
	public AppUser saveUsers(AppUser appUsers);  // business related method : functional requirement

	public AppUser getAllUsersByRole(String role);
	
}
