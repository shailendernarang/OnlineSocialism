package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.BlogComment;

@SuppressWarnings("deprecation")
@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addBlogComment(BlogComment blogComment) {
		Session s = sessionFactory.getCurrentSession();
		s.save(blogComment);
		return true;
	}

	public boolean editBlogComment(BlogComment blogComment) {
		Session s = sessionFactory.getCurrentSession();
		s.update(blogComment);
		return true;
	}

	public boolean deleteBlogComment(Integer blogCommentID) {
		Session s1= sessionFactory.getCurrentSession();
		BlogComment b =(BlogComment)s1.load(BlogComment.class, blogCommentID);
		s1.delete(b);
		return true;
	}

	public BlogComment getBlogComment(Integer blogCommentID) {
		Session s1 =sessionFactory.getCurrentSession();

		@SuppressWarnings("rawtypes")
		Query q = s1.createQuery("from BlogComment where blogCommentID="+blogCommentID);
	    BlogComment b = (BlogComment)q.uniqueResult();
	    return b;
	}

	public List<BlogComment> getAllBlogComments() {
		Session session =sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from BlogComment");
		List<BlogComment> list=query.list();
		return list;
	}

	public List<BlogComment> getBlogComments(int BlogID) {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from BlogComment where blog.blogID="+BlogID);
		List<BlogComment> list=query.list();
		return list;
	}


}
