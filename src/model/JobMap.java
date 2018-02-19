/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * JobMap class is a HashMap that will hold all jobs and their IDs.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class JobMap {

	private HashMap<Integer, Job> myJobs;

	public JobMap() {
		myJobs = new HashMap<Integer, Job>();
	}

	/**
	 * Precondition: theJob must pass all business rules before being added.
	 * Postcondition: theJob is added to a JobMap.
	 * 
	 * @param theJob
	 */
	public void addJob(final Job theJob) {
		myJobs.put(theJob.getJobID(), theJob);
	}

	/**
	 * Store Job maps on local file system. File type must be a .ser.
	 * 
	 * @param theFilename
	 */
	public void storeJobMap(final String theFilename) {
		try {
			FileOutputStream file = new FileOutputStream(theFilename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(myJobs);
			out.close();
			file.close();
		} catch (IOException theIOException) {
			theIOException.printStackTrace();
			System.out.println("Save job information fail!");
		}
	}

	/**
	 * 
	 * @param theVolunteer the specified volunteer.
	 * @return a list of eligible jobs of the specified volunteer.
	 */
	public List<Job> getEligibleJobs(final Volunteer theVolunteer) {
	    Job[] jobs = getSortedJobsArray();
	    List<Job> eligibleJobs = new ArrayList<>();
	    for (int i = 0; i < jobs.length; i++) {
	        if(!theVolunteer.isSameDayConflict(jobs[i])
                    && theVolunteer.isAtLeastMinDays(jobs[i])) {
	            eligibleJobs.add(jobs[i]);
            }
        }
	    return eligibleJobs;
    }

	/**
	 * Load Job map from the local file system.
	 * 
	 * The file must be create by the storeJobMap method.
	 * @param theFilename
	 */
	public void loadJobMap(final String theFilename) {
		try {
			FileInputStream file = new FileInputStream(theFilename);
			ObjectInputStream in = new ObjectInputStream(file);
			myJobs = (HashMap) in.readObject();
			in.close();
			file.close();
		} catch (FileNotFoundException theFileNotFoundException) {
			System.out.println("No such a file!");
			theFileNotFoundException.printStackTrace();
		} catch (IOException theIOException) {
			theIOException.printStackTrace();
			System.out.println("Load job information fail!");
		} catch (ClassNotFoundException theClassNotFoundException) {
			System.out.println("Class not found exception");
			theClassNotFoundException.printStackTrace();
		}
	}

	public Job getJob(final int theJobID) {
		return myJobs.get(theJobID);
	}
	
	public void remove(final Job theJob) {
		myJobs.remove(theJob.getJobID());
	}
	
	public boolean isLessMaxAmountJobs() {
		return size() < Job.MAX_JOB_AMOUNT;
	}

	public int size() {
		return myJobs.size();
	}
	
	/**
	 * Returns a list of jobs between two given dates, inclusive.
	 * 
	 * @param theStartDate the beginning the period.
	 * @param theEndDate the end of the period.
	 * @return jobsWithinPeriod that contains  jobs between theStartDate and theEndDate.
	 */
	public ArrayList<Job> getJobsInPeriod(final LocalDate theStartDate, final LocalDate theEndDate) {
		Job[] sortedJobs = getSortedJobsArray();
		ArrayList<Job> jobsWithinPeriod = new ArrayList<>();
		for (Job job: sortedJobs) {
			if ((job.getStartDate()).isEqual(theStartDate) 
					||( (job.getStartDate()).isBefore(theEndDate) 
							&& job.getStartDate().isAfter(theStartDate)
							&& job.getEndDate().isBefore(theEndDate)
							&& !job.getEndDate().isBefore(job.getStartDate()))
					|| (job.getEndDate()).isEqual(theEndDate) ) {
				jobsWithinPeriod.add(job);
			}
		}
		return jobsWithinPeriod;
	}
	
	/**
	 * Return a sorted job array that prioritizing job that starts first,
	 * or when they starts the same date, job that ends first.
	 * 
	 * @return sorted array of jobs
	 */
	public Job[] getSortedJobsArray() {
		List<Job> valuesList = new ArrayList<>(myJobs.values());
		Collections.sort(valuesList);
		Job[] jobList = new Job[valuesList.size()];
		for (int i = 0; i < valuesList.size(); i++) {
			jobList[i] = valuesList.get(i);
		}
		return jobList;
	}

	public void displayJobs() {
		System.out.println(myJobs.values().toString());
	}
}
