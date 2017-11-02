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
		Session session = sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.get(Blog.class,blogID);
		return blog;
	}

	public List<Blog> getAllBlogs(int approved) {
		Session session =sessionFactory.openSession();
		String querys="";
				if(approved==1)
						querys="from Blog where blogStatus="+approved;
				else
					querys="from Blog where  rejectionReason is null and blogStatus="+approved;

		Query query = session.createQuery(querys);
		List<Blog> list=query.list();
		return list;
	}
	
	public  void approveBlog(Blog blog) {
		Session s = sessionFactory.getCurrentSession();
		s.update(blog);
		
	}

	public List<Blog> getAllBlogsRejected(int rejected) {
		Session session =sessionFactory.openSession();
		
		Query query = session.createQuery("from Blog where rejectionReason!=null and blogStatus="+rejected);
		List<Blog> list=query.list();
		return list;
	}

	public void increaseLikes(int blogId) {
		Session session = sessionFactory.openSession();
		System.out.println("---starting likes ");
		Blog blog = getBlog(blogId);
		System.out.println("Before Like: "+ blog.getLikes());
		blog.setLikes(blog.getLikes() + 1);
		System.out.println("After Like: "+ blog.getLikes());
		session.update(blog);
		
	}

	public void increaseDislikes(int blogId) {
		Session session = sessionFactory.openSession();
		System.out.println("---starting dislikes ");
		Blog blog = getBlog(blogId);
		System.out.println("Before Dislike: "+ blog.getDislikes());
		blog.setDislikes(blog.getDislikes() + 1);
		System.out.println("After Dislike: "+ blog.getDislikes());
		session.update(blog);
			}
	

}
