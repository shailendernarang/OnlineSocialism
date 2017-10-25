package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.ForumComment;

@Repository("forumCommentDAO")
@Transactional
public class ForumCommentDAOImpl implements ForumCommentDAO {

	
	@Autowired
	SessionFactory sessionFactory; 
	
	public boolean addForumComment(ForumComment forumComment) {
		Session s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(forumComment);
		return true;
	}

	public boolean editForumComment(ForumComment forumComment) {
		Session s = sessionFactory.getCurrentSession();
		s.update(forumComment);
		return true;
	}

	public boolean deleteForumComment(Integer forumCommentID) {
		Session s1= sessionFactory.getCurrentSession();
		ForumComment b =(ForumComment)s1.load(ForumComment.class, forumCommentID);
		s1.delete(b);
		return true;
	}

	public ForumComment getForumComment(Integer forumCommentID) {
		Session s1 =sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query q = s1.createQuery("from ForumComment where forumCommentID="+forumCommentID);
	    ForumComment b = (ForumComment)q.uniqueResult();
	    return b;
	}

	public List<ForumComment> getAllForumComments() {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from ForumComment");
		List<ForumComment> list=query.list();
		return list;
	}


}
