package com.ss.SocialistB.service;

import com.ss.SocialistB.model.ProfilePicture;

public interface ProfilePicService {

	void uploadProfilePic(ProfilePicture pro);
	ProfilePicture viewProfilePic(String userName);
}
