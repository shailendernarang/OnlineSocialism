package com.ss.SocialistB.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	Session session;

	public boolean register(User user) {
		
		 session = sessionFactory.getCurrentSession();
	try {
		user.setStatus("Pending");
		session.save(user);
		return true;
	}catch(Exception e) {
		return false;
	}
	}

	//Checks Username Duplicacy
	public boolean isUsernameValid(String username) {

		session = sessionFactory.getCurrentSession();
		User user =(User)session.get(User.class, username);
		if(user==null)
			return true;
		else
			return false;
	}

	public boolean isEmailValid(String email) {
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where emailID=?");
		query.setString(0, email);
		User user=(User) query.uniqueResult();
		if(user==null)
			return true;
		else
			return false;
	}

	public User login(User user) {
		session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where firstName=? and password=?");
		query.setString(0,user.getFirstName());
		query.setString(1, user.getPassword());
		user = (User)query.uniqueResult();
		return user;
	}

	public boolean approveUser(User user) {
		session = sessionFactory.getCurrentSession();
		user.setStatus("Approved");
		session.update(user);
		return true;
		
	}

}
