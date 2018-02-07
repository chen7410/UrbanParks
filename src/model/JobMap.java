package model;

import java.util.HashMap;

/**
 * JobMap class is a HashMap that will hold all the jobs.
 * 
 * @author Group 7
 * @version 2/6/2018
 */
public class JobMap {

	/**
	 * HashMap that holds all the jobs.
	 */
	private HashMap<Integer, Job> myJobs;

	/**
	 * Initializes a HashMap that holds all the jobs.
	 */
	public JobMap() {
		myJobs = new HashMap<Integer, Job>();
	}

	/**
	 * Adds a job to the job map.
	 * 
	 * @param theJob
	 *            The job to add
	 */
	public void addJob(final Job theJob) {
		myJobs.put(theJob.getJobID(), theJob);
	}

	/**
	 * Gets the job from the job map using the job ID
	 * 
	 * @param theJobID
	 *            The job ID
	 * @return the job
	 */
	public Job getJob(final int theJobID) {
		return myJobs.get(theJobID);
	}

	/**
	 * Returns the current size of the job map
	 * 
	 * @return the current size of the job map
	 */
	public int size() {
		return myJobs.size();
	}
}
