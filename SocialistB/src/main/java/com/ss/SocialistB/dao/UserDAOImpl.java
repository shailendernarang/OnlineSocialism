package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	

	public boolean register(User user) {
		
		 Session session = sessionFactory.getCurrentSession();
	try {
		session.save(user);
		return true;
	}catch(Exception e) {
		return false;
	}
	}

	//Checks Username Duplicacy
	public boolean isUsernameValid(String username) {

		 Session session = sessionFactory.getCurrentSession();
		User user =(User)session.get(User.class, username);
		if(user==null)
			return true;
		else
			return false;
	}

	public boolean isEmailValid(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where emailID=?");
		query.setString(0, email);
		User user=(User) query.uniqueResult();
		if(user==null)
			return true;
		else
			return false;
	}

	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where firstName=? and password=?");
		query.setString(0,user.getFirstName());
		query.setString(1, user.getPassword());
		user = (User)query.uniqueResult();
		return user;
	}

	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		
	}

	public User getUserByUserName(String firstName) {
		Session session = sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, firstName);
		return user;
	}
	public boolean isUpdatedEmailValid(String email,String userName)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where emailID=? and firstName!=?");
		query.setString(0,email);
		query.setString(1, userName);
		User user = (User)query.uniqueResult();
		if(user==null)
			return true;
		else
			return false;

	}

}
