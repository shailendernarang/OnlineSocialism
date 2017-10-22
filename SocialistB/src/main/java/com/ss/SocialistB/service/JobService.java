package com.ss.SocialistB.service;

import java.util.List;

import com.ss.SocialistB.model.Job;

public interface JobService {
	
	void addJob(Job job);
	List<Job> getAllJobs();
	Job deleteJob(int delete);
	
}
