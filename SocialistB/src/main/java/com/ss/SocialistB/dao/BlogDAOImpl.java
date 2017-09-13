package com.ss.SocialistB.dao;

import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.Blog;

import oracle.jdbc.proxy.annotation.Post;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;
	Blog blog;
	
	
	public boolean createBlog(Blog blog) {
		try 
		{
		Session s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(blog);
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
			return false;
		}
	}

	public boolean editBlog(Blog blog) {
		Session s = sessionFactory.getCurrentSession();
		blog.setBlogName("YOOYOY");
		s.update(blog);
		return true;
	}

	public boolean deleteBlog(Integer blogID) {
		Session s1= sessionFactory.getCurrentSession();
		Blog b =(Blog)s1.load(Blog.class, blogID);
		s1.delete(b);
		return true;
		
	}

	public Blog getBlog(Integer blogID) {
		Session s1 =sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query q = s1.createQuery("from Blog where blogID="+blogID);
	    Blog b = (Blog)q.getSingleResult();
	    return b;
	}

	public List<Blog> getAllBlogs() {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from Blog");
		List<Blog> list=query.getResultList();
		return list;
	}
	
	public boolean approveBlog(Blog blog) {
		
		Session s = sessionFactory.getCurrentSession();
		blog.setBlogStatus("A");
		s.update(blog);
		return true;
	}

}
