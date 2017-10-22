package com.ss.SocialistB.dao;

import com.ss.SocialistB.model.ProfilePicture;

public interface ProfilePicDAO {

	void uploadProfilePic(ProfilePicture profile);
	ProfilePicture viewProfilePic(String userName);
}
