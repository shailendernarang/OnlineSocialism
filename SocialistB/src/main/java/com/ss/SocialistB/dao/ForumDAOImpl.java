package com.ss.SocialistB.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public boolean createForum(Forum forum) {
		Session s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(forum);
		return true;
	}

	public boolean editForum(Integer forumID) {
		Session s = sessionFactory.getCurrentSession();
		s.update(forumID);
		return true;
	}

	public boolean deleteForum(Integer forumID) {
		Session s1= sessionFactory.getCurrentSession();
		Forum b =(Forum)s1.load(Forum.class, forumID);
		s1.delete(b);
		return true;
	}

	public Forum getForum(Integer forumID) {
		Session s1 =sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query q = s1.createQuery("from Forum where forumID="+forumID);
	    Forum b = (Forum)q.getSingleResult();
	    return b;
	}

	public List<Forum> getAllForums() {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from Forum");
		List<Forum> list=query.getResultList();
		return list;
	}

	public boolean approveForum(Forum forum) {
		Session s = sessionFactory.getCurrentSession();
		forum.setStatus("A");
		s.saveOrUpdate(forum);
		return true;
	}

}
