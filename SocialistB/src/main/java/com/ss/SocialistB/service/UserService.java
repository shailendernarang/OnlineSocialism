package com.ss.SocialistB.service;

import com.ss.SocialistB.model.User;

public interface UserService {

	boolean registerUser(User user);
	boolean isUsernameValid(String Username);
	public boolean isEmailValid(String email);
	public User login(User user);
	public boolean approveUser(User user);
	
}
