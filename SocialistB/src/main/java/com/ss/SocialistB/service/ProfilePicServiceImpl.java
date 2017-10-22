package com.ss.SocialistB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.ProfilePicDAO;
import com.ss.SocialistB.model.ProfilePicture;

@Service
public class ProfilePicServiceImpl implements ProfilePicService {

	@Autowired
	ProfilePicDAO profilePicDAO;
	public void uploadProfilePic(ProfilePicture pro) {
			profilePicDAO.uploadProfilePic(pro);
		
		
	}
	public ProfilePicture viewProfilePic(String userName) {
		return profilePicDAO.viewProfilePic(userName);
	}

}
