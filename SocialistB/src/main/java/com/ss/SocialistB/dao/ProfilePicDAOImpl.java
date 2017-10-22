package com.ss.SocialistB.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.ProfilePicture;

@Repository("profilePicDAO")
@Transactional
public class ProfilePicDAOImpl implements ProfilePicDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public void uploadProfilePic(ProfilePicture profile) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(profile);

		
	}

	public ProfilePicture viewProfilePic(String userName) {
		Session session = sessionFactory.getCurrentSession();
		ProfilePicture profilePicture =(ProfilePicture)session.get(ProfilePicture.class, userName);
		return profilePicture;
	}

}
