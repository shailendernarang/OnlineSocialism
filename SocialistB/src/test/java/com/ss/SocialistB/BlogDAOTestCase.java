package com.ss.SocialistB;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ss.SocialistB.dao.BlogDAO;
import com.ss.SocialistB.model.Blog;


public class BlogDAOTestCase {
	
	static BlogDAO blogDAO;
	Blog blog = new Blog();
	@BeforeClass
	public static void intialize()
	{		
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.ss.SocialistB");
		annotationConfigApplicationContext.refresh();
		blogDAO=(BlogDAO)annotationConfigApplicationContext.getBean("blogDAO");
	}
	
	@Test
	public void createBlogTest()
	{
		
		
		blog.setBlogName("SELENIUM");
		blog.setBlogContent("it is a testing tool");
		blog.setCreateDate(new Date());
		blog.setBlogStatus(true);
		blog.setLikes(0);
		
		assertTrue("Problem in Blog Creation",blogDAO.createBlog(blog));
		
	}
	
	
	@Test
	public void editBlogTest()
	{
		Blog blog = new Blog();
		blog.setBlogID(5);
		blog.setBlogName("SELENIUM");
		blog.setBlogContent("it is a testing tool");
		blog.setCreateDate(new Date());
		blog.setBlogStatus(true);
		blog.setLikes(2);
		
		assertTrue("Problem in Blog Creation",blogDAO.editBlog(blog));
		
	}
	@Test
	public void approveBlogTest()
	{
		Blog blog = new Blog();
		blog.setBlogID(5);
		blog.setBlogStatus(true);
		blog.setLikes(2);
		assertTrue("Problem in Blog Creation",blogDAO.approveBlog(blog));
		
	}
	
	@Test
	public void getAllApprovedBlogTest()
	{
		List<Blog> listBlog =blogDAO.getAllBlogs();
		assertTrue("No approved Blogs",listBlog.size()>0);
	}
	
	

	@Test
	public void deleteBlogTest()
	{
		assertTrue("No approved Blogs",blogDAO.deleteBlog(4));
	}
	
}
