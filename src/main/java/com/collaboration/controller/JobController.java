package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.JobDao;
import com.collaboration.domain.Job;

@RestController
public class JobController {

	@Autowired
	private Job job;
	@Autowired
	private JobDao jobDao;
	
	@GetMapping("/job/list")
      public ResponseEntity<List<Job>> list()
      {
		List<Job> jobs=jobDao.listAllJobs();
		if(jobs==null)
		{
		
			
			return new ResponseEntity<List<Job>>(jobs,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
      }
	

    @GetMapping("/job/get/{jobId}")
    public ResponseEntity<Job> getJob(@PathVariable int jobId)
    {
    	job=jobDao.getJob(jobId);
    	if(job==null)
    	{
    		job=new Job();
    		
    		return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<Job>(job,HttpStatus.OK);
    	
    }

    
    
}


