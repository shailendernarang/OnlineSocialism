package com.ss.SocialistM.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ss.SocialistB.model.Error;
import com.ss.SocialistB.model.User;
import com.ss.SocialistB.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession httpsession;
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		if(!userService.isUsernameValid(user.getFirstName())) {//duplicate result
			
			Error error = new Error(2,"Username Already there");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		if(!userService.isEmailValid(user.getEmailID())) {
			//duplicate result
			
			Error error = new Error(3,"Email Already exists");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		}
		boolean result = userService.registerUser(user);
		if(result)
		{
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		}
		else
		{
			Error error = new Error(1,"Unable To Register");
			return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);

		}
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user)
	{
		User u=userService.login(user);
		if(u==null) {
			Error error = new Error(4,"Invalid Username Or Password");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
			u.setOnline(true);
			try {
			userService.update(u);
			}catch(Exception e){
				Error error = new Error(6,"Unable to update online status");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			httpsession.setAttribute("firstName",u);
			return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
		
	}
 }
