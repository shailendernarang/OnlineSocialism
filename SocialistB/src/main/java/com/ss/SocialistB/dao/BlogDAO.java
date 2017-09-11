package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Blog;

public interface BlogDAO {

	public boolean createBlog(Blog blog);
	public boolean editBlog(Integer blogID);
	public boolean deleteBlog(Integer blogID);
	public Blog getBlog(Integer blogID);
	public List<Blog> getAllBlogs();
	public boolean approveBlog(Blog blog);
}
