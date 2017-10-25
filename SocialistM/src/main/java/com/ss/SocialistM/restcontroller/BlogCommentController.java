package com.ss.SocialistM.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.SocialistB.dao.BlogCommentDAO;
import com.ss.SocialistB.model.BlogComment;
import com.ss.SocialistB.model.Error;
import com.ss.SocialistB.model.User;
import com.ss.SocialistB.service.BlogCommentService;
import com.ss.SocialistB.service.UserService;

@Controller
public class BlogCommentController {
	
	@Autowired
	BlogCommentService blogCommentService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(value="/addBlogComment")
	public ResponseEntity<?> createBlogComment(@RequestBody BlogComment blogComment,HttpSession httpSession)
	{				
		String userName=(String)httpSession.getAttribute("firstName");
		Error error= new Error(13,"Unauthroized Access");
		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		User postedBy= userService.getUserByUserName(userName);
		blogComment.setPostedBy(postedBy);
		blogComment.setBlogCommentDate(new Date());
		try {
			blogCommentService.addBlogComment(blogComment);
	
		
		return new ResponseEntity<BlogComment> (blogComment,HttpStatus.ACCEPTED);

	}
	catch(Exception e){
		return new ResponseEntity<Error> (error,HttpStatus.BAD_REQUEST);

	}
	
		
	}

//	@PutMapping("/editBlogComment/{blogCommentID}")
//	public ResponseEntity<String> editBlog (@PathVariable("blogCommentID")Integer blogId)
//	{
//		
//		BlogComment blogComment = blogCommentService.getBlogComment(blogId);
//		blogCommentService.editBlogComment(blogComment);
//		return new ResponseEntity<String> ("Blog Edited",HttpStatus.ACCEPTED);
//	}
	@GetMapping("/getBlogComments/{blogId}")
	public  ResponseEntity<?> getBlogComments  (@PathVariable int blogId,HttpSession httpSession)
	{
		System.out.println("HEY");
		
		String userName=(String)httpSession.getAttribute("firstName");
		
		Error error= new Error(13,"Unauthroized Access");
		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments = blogCommentService.getBlogComments(blogId);
		System.out.println(blogComments);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
//	@DeleteMapping("/deleteBlogComment/{blogID}")
//	public ResponseEntity<String> deleteBlogComment (@PathVariable("blogID")Integer blogId,@RequestBody BlogComment blogComment)
//	{
//		blogCommentDAO.deleteBlogComment(blogId);
//		return new ResponseEntity<String> ("Blog Deleted",HttpStatus.ACCEPTED);
//
//	}
	
	
}
