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
import java.util.HashMap;

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
	 * Pre-condition: theJob must pass all business rules before being added.
	 * Post-condition:theJob is added myJobs JobMap.
	 * 
	 * @param theJob
	 */
	public void addJob(final Job theJob) {
		myJobs.put(theJob.getJobID(), theJob);
	}

	/**
	 * Store Job maps on local file system.
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
	 * Load Job map from the local file system.
	 * 
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

	public int size() {
		return myJobs.size();
	}

	public Job[] getJobsArray() {
		return myJobs.values().toArray(new Job[0]);
	}

	public void displayJobs() {
		System.out.println(myJobs.values().toString());
	}
}
