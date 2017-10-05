package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.User;

public interface UserDao {

	public boolean isUsernameValid(String username);
	public boolean register(User user);
	public boolean isEmailValid(String email);
	public User login(User user);
	public void update(User user);
	User getUserByUserName(String firstName);
	public boolean isUpdatedEmailValid(String email,String username);
	}
