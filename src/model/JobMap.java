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
 * A class that will be our Job collection. This class is implemented
 * using HashMap. The key of this collection is an Integer which will
 * be the job's ID, the value will be a Job object.
 * 
 * @author  Group 7
 * @version March 5, 2018
 * @see     Collection
 * @see     HashMap
 */
public class JobMap {
	/**
	 * The name of the serialized file that will be used to 
	 * load the jobs' information from.
	 */
	public static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
	/**
	 * Load the saved max job amount.
	 */
	public static final String MAX_JOB_AMOUNT = "MaxJobAmount.ser";
	
	/** The maximum number of pending jobs in the system.*/
	private int myMaxJobAmount = 10;

	private HashMap<Integer, Job> myJobs;

	public JobMap() {
		myJobs = new HashMap<Integer, Job>();
	}
	
	public int getMaxJobAmount() {
		return myMaxJobAmount;
	}
	
	/**
	 * Checks if the system reaches the maximum job amount.
	 * @return true if full, false otherwise.
	 */
	public boolean isFull (){
		return getPendingJobAmount() >= myMaxJobAmount;
	}
	
	/**
	 * Returns the amount of upcoming jobs; does not include jobs
	 * in the past.
	 * 
	 * @return the number of job that the start date is today or after.
	 */
	public int getPendingJobAmount() {
		int count = 0;
		Job[] jobs = getSortedJobsArray();
		for (Job j : jobs) {
			if (!j.isPassed()) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Changes the maximum amount of pending jobs that the system can hold.
	 * theMaxJobAmount has to be a positive integer.
	 * 
	 * @param theMaxJobAmount the new maximum amount.
	 * @throws IllegalArgumentException if theJobAmount <= 0.
	 */
	public void setMaxJobAmount(int theMaxJobAmount) {
		if (theMaxJobAmount <= 0) {
			throw new IllegalArgumentException("Illegal job amount: "
													+ theMaxJobAmount);
			
		}
		myMaxJobAmount = theMaxJobAmount;
	}
	
	/**
	 * Adds a job to this JobMap. Key is theJob ID, value is theJob.
	 * 
	 * @param theJob job that is being added to an JobMap.
	 * 		  		 null will be added if theJob is null. 
	 */
	public void addJob(final Job theJob) {
		myJobs.put(theJob.getJobID(), theJob);
	}

	/**
	 * Stores jobs' data into a serialized file on the local machine. 
	 * File type must be a .ser.
	 * 
	 * @param theFilename The name of the serialized file where jobs' 
	 *                    data are written to.
	 * @throws IOException when an I/O exception 
	 *                     (either failed or interrupted) occurs.
	 */
	public void storeJobMap(final String theFilename) {
		try {
			FileOutputStream file = new FileOutputStream(theFilename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			//out.writeObject(this);
			out.writeObject(myJobs);
			out.close();
			file.close();
			
			FileOutputStream maxJobFile = new FileOutputStream(MAX_JOB_AMOUNT);
			ObjectOutputStream outMaxJob = new ObjectOutputStream(maxJobFile);
			outMaxJob.writeObject(myMaxJobAmount);
			outMaxJob.close();
			maxJobFile.close();
			
		} catch (IOException theIOException) {
			theIOException.printStackTrace();
			System.out.println("Save job information fail!");
		}
	}

	/**
	 * Load Job map from the local file system.
	 * 
	 * The file must be create by the storeJobMap method.
	 * 
	 * @param theFilename
	 */
	@SuppressWarnings("unchecked")
	public void loadJobMap(final String theFilename) {
		try {
			FileInputStream file = new FileInputStream(theFilename);
			ObjectInputStream in = new ObjectInputStream(file);
			myJobs = (HashMap<Integer, Job>) in.readObject();
			in.close();
			file.close();
			
			FileInputStream maxJobFile = new FileInputStream(MAX_JOB_AMOUNT);
			ObjectInputStream inMaxJob = new ObjectInputStream(maxJobFile);
			myMaxJobAmount = (int) inMaxJob.readObject();
			inMaxJob.close();
			maxJobFile.close();
			
			
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
	
	/**
	 * Retrieves a job from this JobMap.
	 * 
	 * @param  theJobID the job ID of the job being retrieved from this JobMap.
	 * @return a job object that matches the job ID or null if the job doesn't exist.
	 */
	public Job getJob(final int theJobID) {
		return myJobs.get(theJobID);
	}
	
	/**
	 * Returns the number of jobs in the system.
	 * The size includes the job in the past.
	 * 
	 * @return the size of this JobMap.
	 */
	public int size() {
		return myJobs.size();
	}
		
	
	/**
	 * Removes a job from this JobMap.
	 * 
	 * @param theJob the job that is being removed from this JobMap.
	 */
	public void remove(final Job theJob) {
		myJobs.remove(theJob.getJobID());
	}
	
	/**
	 * Adds all the eligible jobs that a volunteer can sign up for to an
	 * ArrayList. An eligible job has to satisfy all of the following:
	 *    1. a job that begins at least the minimum number of days
	 *       (specified in Job class:MIN_DAYS_TO_SIGN_UP) after the 
	 *       current date. 
	 *    2. a job that does NOT extend any particular calendar day.
	 *
	 * @param  theVolunteer the volunteer specified.
	 * @return a list of eligible jobs of the specified volunteer.
	 */
	public List<Job> getEligibleJobs(final Volunteer theVolunteer) {
	    Job[] jobs = getSortedJobsArray();
	    List<Job> eligibleJobs = new ArrayList<>();
	    for (int i = 0; i < jobs.length; i++) {
	        if(!theVolunteer.isSameDayConflict(jobs[i], this)
                    && theVolunteer.isAtLeastMinDays(jobs[i])) {
	            eligibleJobs.add(jobs[i]);
            }
        }
	    return eligibleJobs;
    }

	/**
	 * Checks if the current size of the JobMap is less
	 * than the maximum allowed number of pending jobs
	 * in the system.
	 * 
	 * @return true if the current size is less than the maximum allowed
	 * 			    number of jobs in system; false otherwise.
	 */
	public boolean isLessMaxAmountJobs() {
		return size() < myMaxJobAmount;
	}
	

	/**
	 * Searches this JobMap for jobs that start on the specified
	 * start date and end on the specified end date, and all the 
	 * jobs that fall in between the two dates. These jobs are added
	 * to an ArrayList that is returned to this method caller. theStartDate 
	 * and theEndDate cannot be null and theStartDate cannot be after theEndDate.
	 * 
	 * @param theStartDate
	 *            the beginning the period.
	 * @param theEndDate
	 *            the end of the period.
	 * @return jobsWithinPeriod that contains jobs between 
	 * 			theStartDate and theEndDate.
	 * @throws IllegalArgumentException if theStartDate or theEndDate is null,
	 * 									or if theStartDate is after theEndDate.
	 */
	public ArrayList<Job> getJobsInPeriod(final LocalDate theStartDate,
											final LocalDate theEndDate) {
		if (theStartDate == null) {
			throw new IllegalArgumentException(
										"Start date cannot be null");
		}
		if (theEndDate == null) {
			throw new IllegalArgumentException(
										"End date cannot be null");
		}
		if (theStartDate.isAfter(theEndDate)) {
			throw new IllegalArgumentException(
							"Start date cannot be after end date");
		}
		Job[] sortedJobs = getSortedJobsArray();
		ArrayList<Job> jobsWithinPeriod = new ArrayList<>();
		for (Job job : sortedJobs) {
			if (job.isJobWithinDates(theStartDate, theEndDate)) {
				jobsWithinPeriod.add(job);
			}
		}
		return jobsWithinPeriod;
	}

	/**
	 * Returns a sorted Job array. The job with the earliest 
	 * start date will be at index = 0. The job with the latest 
	 * start date will be at index = array.length-1. If two jobs 
	 * have the same start date, the job that ends first will be 
	 * placed first.
	 * 
	 * @return an array with sorted jobs.
	 */
	public Job[] getSortedJobsArray() {
		List<Job> valuesList = new ArrayList<>(myJobs.values());
		Collections.sort(valuesList);
		for (Job j : valuesList) {
			if (j == null) {
				valuesList.remove(j);
			}
		}
		Job[] jobList = new Job[valuesList.size()];
		for (int i = 0; i < valuesList.size(); i++) {
			jobList[i] = valuesList.get(i);
		}
		return jobList;
	}
	
	/**
	 * Displays all the jobs in this JobMap in the console. 
	 */
	public void displayJobs() {
		System.out.println(myJobs.values().toString());
	}
}
