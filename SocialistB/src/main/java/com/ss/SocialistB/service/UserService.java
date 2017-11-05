package com.ss.SocialistB.service;

import java.util.List;

import com.ss.SocialistB.model.User;

public interface UserService {

	boolean registerUser(User user);
	boolean isUsernameValid(String Username);
	public boolean isEmailValid(String email);
	public User login(User user);
	public void update(User user);
	User getUserByUserName(String firstName);
	public boolean isUpdatedEmailValid(String email,String username);
	List<User> getAllUser();
	
}
