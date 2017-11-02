package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Blog;

public interface BlogDAO {

	public boolean createBlog(Blog blog);
	public boolean editBlog(Blog blog);
	public boolean deleteBlog(Integer blogID);
	public Blog getBlog(Integer blogID);
	public List<Blog> getAllBlogs(int approved);
	public List<Blog> getAllBlogsRejected(int rejected);
	void increaseLikes(int blogId);
	
	void increaseDislikes(int blogId);
	public void approveBlog(Blog blog);
}
