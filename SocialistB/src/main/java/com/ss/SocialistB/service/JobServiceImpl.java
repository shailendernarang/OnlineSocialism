package com.ss.SocialistB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.SocialistB.dao.JobDAO;
import com.ss.SocialistB.model.Job;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobDAO jobDAO;
	public void addJob(Job job) {
		jobDAO.addJob(job);

	}
	public List<Job> getAllJobs() {
		return jobDAO.getAllJobs();
	}
	public Job deleteJob(int delete) {
		return jobDAO.deleteJob(delete);
		
	}


}
