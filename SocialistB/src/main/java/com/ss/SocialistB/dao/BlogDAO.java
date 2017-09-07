package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Blog;

public interface BlogDAO {

	public boolean createBlog(Blog blog);
	public boolean editBlog(int blogID);
	public boolean deleteBlog(int blogID);
	public Blog getBlog(int blogID);
	public List<Blog> getAllBlogs();
	public boolean approveBlog(Blog blog);
}
