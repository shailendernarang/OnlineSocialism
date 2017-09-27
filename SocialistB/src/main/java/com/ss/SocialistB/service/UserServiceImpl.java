package com.ss.SocialistB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.UserDao;
import com.ss.SocialistB.model.User;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserDao userDao;

	public boolean registerUser(User user) {
		return userDao.register(user);
	}

	public boolean isUsernameValid(String username) {
		return userDao.isUsernameValid(username);
	}

	public boolean isEmailValid(String email) {
		return userDao.isEmailValid(email);
	}

	public User login(User user) {
		
		return userDao.login(user);
	}

	public boolean approveUser(User user) {
		return userDao.approveUser(user);
	}

}
