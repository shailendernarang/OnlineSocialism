package com.ss.SocialistB.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss.SocialistB.model.Blog;
import com.ss.SocialistB.model.Job;
@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public void addJob(Job job) {
	Session s = sessionFactory.getCurrentSession();
	job.setPostedOn(new Date());
	s.save(job);

	}

	public List<Job> getAllJobs() {
		Session session =sessionFactory.openSession();
		Query query = session.createQuery("from Job");
		List<Job> list=query.getResultList();
		return list;
	}

	public Job deleteJob(int delete) {
		Session s = sessionFactory.getCurrentSession();
		Job job=(Job)s.get(Job.class,delete);
		s.delete(job);
		return job;
		
	}

	
}
