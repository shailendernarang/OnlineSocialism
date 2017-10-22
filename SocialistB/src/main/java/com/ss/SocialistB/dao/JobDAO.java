package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.Job;

public interface JobDAO {
	
	void addJob(Job job);
	public List<Job> getAllJobs();
	Job deleteJob(int delete);
	
}
