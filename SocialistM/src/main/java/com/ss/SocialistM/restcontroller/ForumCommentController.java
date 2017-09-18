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

import com.ss.SocialistB.dao.ForumCommentDAO;
import com.ss.SocialistB.model.ForumComment;

@RestController
public class ForumCommentController {
	
	@Autowired
	ForumCommentDAO forumCommentDAO;
	
	
	@GetMapping(value="/getAllForumComments()")
	public ResponseEntity<ArrayList<ForumComment>> getAllForumComments()
	{
		ArrayList<ForumComment> listForum = new ArrayList<ForumComment>();
		listForum = (ArrayList<ForumComment>)forumCommentDAO.getAllForumComments();
		return new  ResponseEntity<ArrayList<ForumComment>>(listForum,HttpStatus.ACCEPTED);
	}
	@PostMapping(value="/addForumComment")
	public ResponseEntity<String> createForumComment(@RequestBody ForumComment forumComment)
	{				
		forumComment.setForumCommentDate(new Date());
	if(forumCommentDAO.addForumComment(forumComment))
	{
		
		return new ResponseEntity<String> ("Forum Commented Created",HttpStatus.ACCEPTED);

	}
	else {
		return new ResponseEntity<String> ("Forum Problem",HttpStatus.METHOD_FAILURE);

	}
	
		
	}

	@PutMapping("/editForumComment/{forumCommentID}")
	public ResponseEntity<String> editBlog (@PathVariable("forumCommentID")Integer forumId)
	{
		
		ForumComment forumComment = forumCommentDAO.getForumComment(forumId);
		forumCommentDAO.editForumComment(forumComment);
		return new ResponseEntity<String> ("Forum Comment Edited",HttpStatus.ACCEPTED);
	}
	@GetMapping("/getForumComment/{forumID}")
	public ForumComment getForum (@PathVariable("forumID") int forumId)
	{
		
		return forumCommentDAO.getForumComment(forumId);

	}
	
	@DeleteMapping("/deleteForumComment/{forumID}")
	public ResponseEntity<String> deleteForumComment (@PathVariable("forumID")Integer forumId,@RequestBody ForumComment forumComment)
	{
		forumCommentDAO.deleteForumComment(forumId);
		return new ResponseEntity<String> ("Forum Comment Deleted",HttpStatus.ACCEPTED);

	}
	
	
}
