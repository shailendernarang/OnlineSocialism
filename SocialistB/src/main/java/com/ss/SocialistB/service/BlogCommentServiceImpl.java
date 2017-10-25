package com.ss.SocialistB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.BlogCommentDAO;
import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.BlogComment;

@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	BlogCommentDAO blogCommentDAO;
	public boolean addBlogComment(BlogComment blogComment) {
		return blogCommentDAO.addBlogComment(blogComment);
	}
	public List<BlogComment> getBlogComments(int blogId) {
		return  blogCommentDAO.getBlogComments(blogId);
	}
	public BlogComment getBlogComment(int blogCommentID) {
		return blogCommentDAO.getBlogComment(blogCommentID);
	}

}
