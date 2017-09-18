package com.ss.SocialistM.restcontroller;

import java.util.ArrayList;
import java.util.Date;

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

import com.ss.SocialistB.dao.BlogCommentDAO;
import com.ss.SocialistB.model.BlogComment;

@RestController
public class BlogCommentController {
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@GetMapping(value="/getAllBlogComments()")
	public ResponseEntity<ArrayList<BlogComment>> getAllBlogComments()
	{
		ArrayList<BlogComment> listBlogs = new ArrayList<BlogComment>();
		listBlogs = (ArrayList<BlogComment>)blogCommentDAO.getAllBlogComments();
		return new  ResponseEntity<ArrayList<BlogComment>>(listBlogs,HttpStatus.ACCEPTED);
	}
	@PostMapping(value="/addBlogComment")
	public ResponseEntity<String> createBlogComment(@RequestBody BlogComment blogComment)
	{				
		blogComment.setBlogCommentDate(new Date());
	if(blogCommentDAO.addBlogComment(blogComment))
	{
		
		return new ResponseEntity<String> ("Blog Created",HttpStatus.ACCEPTED);

	}
	else {
		return new ResponseEntity<String> ("Blog Problem",HttpStatus.METHOD_FAILURE);

	}
	
		
	}

	@PutMapping("/editBlogComment/{blogCommentID}")
	public ResponseEntity<String> editBlog (@PathVariable("blogCommentID")Integer blogId)
	{
		
		BlogComment blogComment = blogCommentDAO.getBlogComment(blogId);
		blogCommentDAO.editBlogComment(blogComment);
		return new ResponseEntity<String> ("Blog Edited",HttpStatus.ACCEPTED);
	}
	@GetMapping("/getBlogComment/{blogID}")
	public BlogComment getBlog (@PathVariable("blogID") int blogId)
	{
		
		return blogCommentDAO.getBlogComment(blogId);

	}
	
	@DeleteMapping("/deleteBlogComment/{blogID}")
	public ResponseEntity<String> deleteBlogComment (@PathVariable("blogID")Integer blogId,@RequestBody BlogComment blogComment)
	{
		blogCommentDAO.deleteBlogComment(blogId);
		return new ResponseEntity<String> ("Blog Deleted",HttpStatus.ACCEPTED);

	}
	
	
}
