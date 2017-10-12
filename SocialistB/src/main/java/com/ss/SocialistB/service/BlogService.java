package com.ss.SocialistB.service;

import java.util.List;

import com.ss.SocialistB.model.Blog;

public interface BlogService {
	
	public boolean createBlog(Blog blog);
	public List<Blog> getAllBlogs(int approved);

	public void approveBlog(Blog blog);
	Blog getBlog(int id);
	public List<Blog> getAllBlogsRejected(int rejected);

}
