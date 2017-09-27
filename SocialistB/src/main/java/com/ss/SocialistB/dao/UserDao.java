package com.ss.SocialistB.dao;

import com.ss.SocialistB.model.User;

public interface UserDao {

	public boolean isUsernameValid(String username);
	public boolean register(User user);
	public boolean isEmailValid(String email);
	public User login(User user);
	public boolean approveUser(User user);
}
