package com.ss.SocialistB.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ss.SocialistB.model.Blog;

public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean createBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean editBlog(int blogID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBlog(int blogID) {
		// TODO Auto-generated method stub
		return false;
	}

	public Blog getBlog(int blogID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean approveBlog(Blog blog) {
		// TODO Auto-generated method stub
		return false;
	}

}
