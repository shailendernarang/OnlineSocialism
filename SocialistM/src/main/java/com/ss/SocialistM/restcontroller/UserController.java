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
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
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
			session.setAttribute("firstName",u.getFirstName());
			return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
		
	}
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session)
	{
		String firstName=session.getAttribute("firstName").toString();
		if(firstName==null) {
			Error error = new Error(7,"UNAUTHORIZED");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User user = userService.getUserByUserName(firstName);
		user.setOnline(false);
		userService.update(user);
		session.removeAttribute("firstName");
		session.invalidate();
		return new ResponseEntity<String>("Logout",HttpStatus.ACCEPTED);
	}
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	
	public ResponseEntity<?> getUser(HttpSession session)
	{
		String userName = (String)session.getAttribute("firstName");
		if(userName==null)
		{
			Error error = new Error(7,"UNAUTHORIZED");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		
		}
		User user = userService.getUserByUserName(userName);
		return new ResponseEntity<User>(user,HttpStatus.OK);

	}
 }
