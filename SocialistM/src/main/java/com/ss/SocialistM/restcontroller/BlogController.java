package com.ss.SocialistM.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.SocialistB.dao.BlogDAO;
import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.Error;
import com.ss.SocialistB.model.User;
import com.ss.SocialistB.service.BlogService;
import com.ss.SocialistB.service.UserService;

@RestController
public class BlogController
{
	
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	UserService userService;
	@Autowired 
	BlogService blogService;
//	
//	@GetMapping(value="/getAllBlogs")
//	public ResponseEntity<ArrayList<Blog>> getAllBlogs()
//	{
//		ArrayList<Blog> listBlogs = new ArrayList<Blog>();
//		listBlogs = (ArrayList<Blog>)blogDAO.getAllBlogs();
//		return new  ResponseEntity<ArrayList<Blog>>(listBlogs,HttpStatus.ACCEPTED);
//	}
	
	@RequestMapping(value="/createBlog",method=RequestMethod.POST)
	public ResponseEntity<?> createBlog(@RequestBody Blog blog,HttpSession httpSession)
	{				
		String userName=(String)httpSession.getAttribute("firstName");
		Error error= new Error(13,"Unauthroized Access");
		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		blog.setCreateDate(new Date());
		User postedBy=userService.getUserByUserName(userName);
		blog.setPostedBy(postedBy);
					try 
					{
		
						blogDAO.createBlog(blog);
						System.out.println("name"+blog.getBlogName());
						return new ResponseEntity<Blog> (blog,HttpStatus.OK);
					}
					catch(Exception e)
					{
						return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
					}
	
		
	}
	
//	@PutMapping("/getBlogById/{blogID}")
//	public ResponseEntity<String> approve (@PathVariable("blogID")Integer blogId)
//	{
//		Blog blog = blogDAO.getBlog(blogId);
//		
//		if(blogDAO.approveBlog(blog))
//		{
//			return new ResponseEntity<String> ("Blog Approved",HttpStatus.ACCEPTED);
//
//		}
//		else
//		{
//			return new ResponseEntity<String> ("Blog Not Approved",HttpStatus.BAD_GATEWAY);
//
//		}
//
//	}
	
	@DeleteMapping("/deleteBlog/{blogID}")
	public ResponseEntity<String> deleteBlog (@PathVariable("blogID")Integer blogId,@RequestBody Blog blog)
	{
		blogDAO.deleteBlog(blogId);
		return new ResponseEntity<String> ("Blog Deleted",HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<?> getBlog (@PathVariable int blogId,HttpSession httpSession)
	{		String userName=(String)httpSession.getAttribute("firstName");
	Error error= new Error(13,"Unauthroized Access");

		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		Blog blog = blogService.getBlog(blogId);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);

	}
	
	@PutMapping("/editBlog/{blogID}")
	public ResponseEntity<String> editBlog (@PathVariable("blogID")Integer blogId)
	{
		
		Blog blog = blogDAO.getBlog(blogId);
		blogDAO.editBlog(blog);
		return new ResponseEntity<String> ("Blog Edited",HttpStatus.ACCEPTED);
	}

	@RequestMapping(value="/getAllBlogs/{approved}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogs (@PathVariable int approved,HttpSession httpSession)
	{
		String userName=(String)httpSession.getAttribute("firstName");
		Error error= new Error(11,"Unauthroized Access");
		
		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Blog> blog=blogDAO.getAllBlogs(approved);
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/approveBlog",method=RequestMethod.PUT)

	public ResponseEntity<?> approveBlog(@RequestBody Blog blog,HttpSession httpSession)
	{
		String userName=(String)httpSession.getAttribute("firstName");
		
		System.out.println("I AM HERE");
		if(userName==null)
		{
			Error error= new Error(11,"Unauthroized Access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	//	if(!blog.isBlogStatus() && blog.getRejectionReason()==null)
		//{
			//blog.setRejectionReason("Not Mentioned");
		//}
		blogService.approveBlog(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllBlogsRejected/{rejected}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogsRejected (@PathVariable int rejected,HttpSession httpSession)
	{
		String userName=(String)httpSession.getAttribute("firstName");
		Error error= new Error(11,"Unauthroized Access");
		
		if(userName==null)
		{
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Blog> blog=blogService.getAllBlogsRejected(rejected);
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.ACCEPTED);
	}
}
