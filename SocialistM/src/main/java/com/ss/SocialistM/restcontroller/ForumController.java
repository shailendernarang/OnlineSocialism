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

import com.ss.SocialistB.dao.ForumDAO;
import com.ss.SocialistB.model.Forum;

@RestController
public class ForumController
{
	
	@Autowired
	ForumDAO forumDAO;
	
	@GetMapping(value="/getAllForums")
	public ResponseEntity<ArrayList<Forum>> getAllForums()
	{
		ArrayList<Forum> listForum = new ArrayList<Forum>();
		listForum = (ArrayList<Forum>)forumDAO.getAllForums();
		return new  ResponseEntity<ArrayList<Forum>>(listForum,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/createForum")
	public ResponseEntity<String> createForum(@RequestBody Forum forum)
	{				
		forum.setCreateDate(new Date());
	if(forumDAO.createForum(forum))
	{
		
		return new ResponseEntity<String> ("Forum Created",HttpStatus.ACCEPTED);

	}
	else {
		return new ResponseEntity<String> ("Forum Problem",HttpStatus.METHOD_FAILURE);

	}
	
		
	}
	
	@PutMapping("/approveForum/{forumID}")
	public ResponseEntity<String> approveForum (@PathVariable("forumID")Integer forumId)
	{
		Forum forum = forumDAO.getForum(forumId);
		
		if(forumDAO.approveForum(forum))
		{
			return new ResponseEntity<String> ("forum Approved",HttpStatus.ACCEPTED);

		}
		else
		{
			return new ResponseEntity<String> ("forum Not Approved",HttpStatus.BAD_GATEWAY);

		}

	}
	
	@DeleteMapping("/deleteForum/{forumID}")
	public ResponseEntity<String> deleteForum (@PathVariable("forumID")Integer forumId,@RequestBody Forum forum)
	{
		forumDAO.deleteForum(forumId);
		return new ResponseEntity<String> ("Forum Deleted",HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/getForum/{forumID}")
	public Forum getForum (@PathVariable("forumID") int forumId)
	{
		
		return forumDAO.getForum(forumId);

	}
	
	@PutMapping("/editForum/{forumID}")
	public ResponseEntity<String> editForum (@PathVariable("forumID")Integer forumId)
	{
		
		Forum forum = forumDAO.getForum(forumId);
		forumDAO.editForum(forum);
		return new ResponseEntity<String> ("Forum Edited",HttpStatus.ACCEPTED);
	}

}
