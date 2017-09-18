package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addBlogComment(BlogComment blogComment) {
		Session s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(blogComment);
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
		@SuppressWarnings("unchecked")
		Query q = s1.createQuery("from BlogComment where blogCommentID="+blogCommentID);
	    BlogComment b = (BlogComment)q.getSingleResult();
	    return b;
	}

	public List<BlogComment> getAllBlogComments() {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from BlogComment");
		List<BlogComment> list=query.getResultList();
		return list;
	}


}
