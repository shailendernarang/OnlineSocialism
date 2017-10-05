package com.ss.SocialistM.restcontroller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ss.SocialistB.dao.BlogDAO;
import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.Error;
import com.ss.SocialistB.model.User;
import com.ss.SocialistB.service.UserService;

@RestController
public class BlogController
{
	
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	UserService userService;
	
	@GetMapping(value="/getAllBlogs")
	public ResponseEntity<ArrayList<Blog>> getAllBlogs()
	{
		ArrayList<Blog> listBlogs = new ArrayList<Blog>();
		listBlogs = (ArrayList<Blog>)blogDAO.getAllBlogs();
		return new  ResponseEntity<ArrayList<Blog>>(listBlogs,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/createBlog")
	public ResponseEntity<?> createBlog(@RequestBody Blog blog,HttpSession httpSession)
	{				
		String userName=(String)httpSession.getAttribute("firstName");
		if(userName==null)
		{
			Error error= new Error(11,"Unauthroized Access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blog.setCreateDate(new Date());
		User postedBy=userService.getUserByUserName(userName);
		blog.setPostedBy(postedBy);
	if(blogDAO.createBlog(blog))
	{
		
		return new ResponseEntity<String> ("Blog Created",HttpStatus.ACCEPTED);

	}
	else {
		return new ResponseEntity<String> ("Blog Problem",HttpStatus.METHOD_FAILURE);

	}
	
		
	}
	
	@PutMapping("/approveBlog/{blogID}")
	public ResponseEntity<String> approveBlog (@PathVariable("blogID")Integer blogId)
	{
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String> ("Blog Approved",HttpStatus.ACCEPTED);

		}
		else
		{
			return new ResponseEntity<String> ("Blog Not Approved",HttpStatus.BAD_GATEWAY);

		}

	}
	
	@DeleteMapping("/deleteBlog/{blogID}")
	public ResponseEntity<String> deleteBlog (@PathVariable("blogID")Integer blogId,@RequestBody Blog blog)
	{
		blogDAO.deleteBlog(blogId);
		return new ResponseEntity<String> ("Blog Deleted",HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/getBlog/{blogID}")
	public Blog getBlog (@PathVariable("blogID") int blogId)
	{
		
		return blogDAO.getBlog(blogId);

	}
	
	@PutMapping("/editBlog/{blogID}")
	public ResponseEntity<String> editBlog (@PathVariable("blogID")Integer blogId)
	{
		
		Blog blog = blogDAO.getBlog(blogId);
		blogDAO.editBlog(blog);
		return new ResponseEntity<String> ("Blog Edited",HttpStatus.ACCEPTED);
	}

}
