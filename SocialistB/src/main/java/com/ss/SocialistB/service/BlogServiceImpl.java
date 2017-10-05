package com.ss.SocialistB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.BlogDAO;
import com.ss.SocialistB.model.Blog;

@Service
public class BlogServiceImpl implements BlogService {
@Autowired
BlogDAO blogDao;
	public boolean createBlog(Blog blog) {
		return blogDao.createBlog(blog);
	}

	public List<Blog> getAllBlogs(int approved) {
		return blogDao.getAllBlogs(approved);
	}
	

}
