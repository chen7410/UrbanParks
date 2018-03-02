package test_ui;

import java.time.LocalDate;
import java.util.List;

import model.Job;
import model.JobMap;
import model.ParkManager;
import model.Staff;
import model.UserMap;
import model.Volunteer;

public class addUserIgnoreThisClass {
	private static UserMap myUsers;
	private static JobMap myJobs;
	private static LocalDate myStartDate = LocalDate.now().plusMonths(-1);
	private static LocalDate myEndtDate = LocalDate.now().plusMonths(1);
	
	public static void main(String[] args) {
		myUsers = new UserMap();
		myJobs = new JobMap();
//		myUsers.loadUserMap(myUsers.USERS_DATA_FILE);
//		myJobs.loadJobMap(myJobs.JOBS_DATA_FILE);
		//Staff tom = new Staff("tom", "Tom", "Hanks");
		//myUsers.addUser(tom);
//		System.out.println(myUsers.getUser("tom"));
//		System.out.println(myUsers.getAllUserNames().toString());
		
		//System.out.println(myJobs.getJobsInPeriod(myStartDate, myEndtDate));
		//List<Job> joblist = myJobs.getJobsInPeriod(myStartDate, myEndtDate);
		resetSystemJobsUsers();
		Job[] joblist = myJobs.getSortedJobsArray();
		
		for (Job j : joblist) {
			System.out.println(j.toString());
		}
		
		System.out.println(myUsers.getAllUserNames().toString());
		

		
	}
	
	
	
	
	/*****************************************************************/
	
	public static void resetSystemJobsUsers() {
		
		
		ParkManager matt = new ParkManager("matthew", "Minqing",  "Chen");
		ParkManager brook = new ParkManager("brook", "Brook", "Negussie");
		Volunteer hasnah = new Volunteer("hasnah", "Hasnah", "Said");
		Volunteer tuan = new Volunteer("tuan", "Tuan", "Dinh");
		Staff john = new Staff("john", "John", "Doe");
		
		myJobs = new JobMap();
		myUsers = new UserMap();
		
		myUsers.addUser(matt);
		myUsers.addUser(brook);
		myUsers.addUser(hasnah);
		myUsers.addUser(tuan);
		myUsers.addUser(john);
		
		Job job1 = new Job (LocalDate.of(2018, 03, 01), 
				LocalDate.of(2018, 03, 24), 
				"Seward Park", matt, 
				"5900 Lake Washington Blvd S, Seattle, WA 98118",
				"Removing invasive species and covering the site with burlap and mulch");
		matt.createJob(job1);

			
		Job job2 = new Job (LocalDate.of(2018, 03, 25) 
				, LocalDate.of(2018, 03, 26)
				, "Discovery Park", brook 
				, "3801 Discovery Park Blvd, Seattle, WA 98199" 
				, "Replacing playground equipments");
		
		brook.createJob(job2);
		
		Job job3 = new Job(LocalDate.of(2018, 03, 24)
				, LocalDate.of(2018, 03, 26)
				, "Gas Works Park", matt
				, "2101 N Northlake Way, Seattle, WA 98103"
				, "Replacing playground equipments");
		
		matt.createJob(job3);
		
		Job job4 = new Job(LocalDate.of(2018, 03, 17)
				, LocalDate.of(2018, 03, 19)
				, "Lincoln Park", brook
				, "8011 Fauntleroy Way SW, Seattle, WA 98136"
				, "Planting native plants");
		brook.createJob(job4);
		
		Job job5 = new Job(LocalDate.of(2018, 03, 18)
				, LocalDate.of(2018, 03, 20)
				, "Volunteer Park", matt
				, "1247 15th Ave E, Seattle, WA 98112"
				, "Restoring and beautifying the landscape of the park");
		matt.createJob(job5);
		
		
		
		Job job6 = new Job (LocalDate.of(2018, 03, 19), 
				LocalDate.of(2018, 03, 20), 
				"Crown Hill Park", matt, 
				"9089 Holman Rd NW, Seattle, WA 98117",
				"Park grounds cleanup");
		matt.createJob(job6);

			
		Job job7 = new Job (LocalDate.of(2018, 03, 19) 
				, LocalDate.of(2018, 03, 21)
				, "Carkeek Park", matt 
				, "950 NW Carkeek Park Rd, Seattle, WA 98177"
				, "Removing non-native invasive plants");	
		matt.createJob(job7);
		
		
		
		Job job8 = new Job(LocalDate.of(2018, 04, 01)
				, LocalDate.of(2018, 04, 03)
				, "Salmon Bay Park", brook
				, "2001 NW Canoe Pl, Seattle, WA 98117"
				, "Setting up and tearing down stage");
		brook.createJob(job8);
		
		
		Job job9 = new Job(LocalDate.of(2018, 04, 01)
				, LocalDate.of(2018, 04, 02)
				, "Sunset Hill Park", brook
				, "7531 34th Ave NW, Seattle, WA 98117"
				, "Planting native plants");
		brook.createJob(job9);
		
		
		
//		Job job10 = new Job(LocalDate.of(2018, 04, 04)
//				, LocalDate.of(2018, 04, 05)
//				, "Woodland Park", matt
//				, "5500 Phinney Ave N, Seattle, WA 98103"
//				, "Planting and mulching flowerbeds");
//		matt.createJob(job10);
//		
//		
//		
//		Job job11 = new Job (LocalDate.of(2018, 04, 06), 
//				LocalDate.of(2018, 04, 07), 
//				"Kerry Park", matt, 
//				"211 W Highland Dr, Seattle, WA 98119",
//				"Weeding and mulching flowerbeds");
//		matt.createJob(job11);
//
//			
//		Job job12 = new Job (LocalDate.of(2018, 04, 8) 
//				, LocalDate.of(2018, 04, 8)
//				, "Greenwood Park", brook 
//				, "8905 Fremont Ave N, Seattle, WA 98103" 
//				, "Resurfacing the limestone running trail");
//		
//		brook.createJob(job12);
//		
//		Job job13 = new Job(LocalDate.of(2018, 04, 8)
//				, LocalDate.of(2018, 04, 8)
//				, "Ballard Commons Park", matt
//				, "5701 22nd Ave NW, Seattle, WA 98107"
//				, "Park grounds cleanup");
//		
//		matt.createJob(job13);
//		
//		
//		Job job14 = new Job(LocalDate.of(2018, 04, 13)
//				, LocalDate.of(2018, 04, 14)
//				, "Volunteer Park", matt
//				, "1247 15th Ave E, Seattle, WA 98112"
//				, "Restore and beautify the landscape of the park");
//		matt.createJob(job14);
//		
//		
//		
//		Job job15 = new Job (LocalDate.of(2018, 04, 15), 
//				LocalDate.of(2018, 04, 16), 
//				"Kerry Park", matt, 
//				"211 W Highland Dr, Seattle, WA 98119",
//				"Weeding and mulching flowerbeds");
//		matt.createJob(job15);
//
//			
//		Job job16 = new Job (LocalDate.of(2018, 04, 16) 
//				, LocalDate.of(2018, 04, 18)
//				, "Clark Lake Park", brook 
//				, "12520 SE 240th St, Kent, WA 98031" 
//				, "Monitoring and crowd flow management at upcoming event");
//		
//		brook.createJob(job16);
//		
//		Job job17 = new Job(LocalDate.of(2018, 04, 18)
//				, LocalDate.of(2018, 04, 19)
//				, "Lake Meridian Park", matt
//				, "14800 SE 272nd St, Kent, WA 98030"
//				, "Park grounds cleanup");
//		
//		matt.createJob(job17);
//		
//		
//		Job job18 = new Job(LocalDate.of(2018, 05, 01)
//				, LocalDate.of(2018, 05, 03)
//				, "Jefferson Park", brook
//				, "3801 Beacon Ave S, Seattle, WA 98108"
//				, "Removing non-native invasive plants");
//		brook.createJob(job18);
//		
//		Job job19 = new Job(LocalDate.of(2018, 05, 02)
//				, LocalDate.of(2018, 05, 04)
//				, "Dearborn Park", matt
//				, "2919 S Brandon St, Seattle, WA 98108"
//				, "Replacing playground equipments");
//		matt.createJob(job19);
		
		
		
		
		myJobs.addJob(job1);
		myJobs.addJob(job2);
		myJobs.addJob(job3);
		myJobs.addJob(job4);
		myJobs.addJob(job5);
		myJobs.addJob(job6);
		myJobs.addJob(job7);
		myJobs.addJob(job8);
		myJobs.addJob(job9);
//		myJobs.addJob(job10);
//		myJobs.addJob(job11);
//		myJobs.addJob(job12);
//		myJobs.addJob(job13);
//		myJobs.addJob(job14);
//		myJobs.addJob(job15);
//		myJobs.addJob(job16);
//		myJobs.addJob(job17);
//		myJobs.addJob(job18);
//		myJobs.addJob(job19);
		
		myJobs.storeJobMap("UpcomingJobs.ser");		
		myUsers.storeUserMap("UsersInformations.ser");		

		
	}
	

}
