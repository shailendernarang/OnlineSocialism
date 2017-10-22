package com.ss.SocialistM.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ss.SocialistB.model.Error;
import com.ss.SocialistB.model.ProfilePicture;
import com.ss.SocialistB.model.User;
import com.ss.SocialistB.service.ProfilePicService;

@Controller
public class ProfilePicController {

	@Autowired
	ProfilePicService profilePicService;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		User users=(User)session.getAttribute("firstName");
		if(users==null)		{
			    Error error=new Error(3,"UnAuthorized user");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		} 
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUserName(users.getFirstName());
		profilePicture.setImage(image.getBytes());
		profilePicService.uploadProfilePic(profilePicture);
		return new ResponseEntity<User>(users,HttpStatus.OK);
	}
		
	
	@RequestMapping(value="/getImage/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null)
			return null;
		else
		{
			ProfilePicture profilePic=profilePicService.viewProfilePic(username);
			if(profilePic==null)
				return null;
			else
				return profilePic.getImage();
		}
		
}
}

