package ui;

import java.time.LocalDate;

import model.Job;
import model.JobMap;
import model.ParkManager;
import model.UserMap;
import model.Volunteer;

public class Main {

	static JobMap myJobs = new JobMap();
	static UserMap myUsers = new UserMap();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadJobs();
	}

	
	
	/*****************************************************************/
	
	public static void loadJobs() {
		
		
		ParkManager matt = new ParkManager("matthew", "Minqin",  "Chen");
		ParkManager brook = new ParkManager("brook", "Brook", "Negussie");
		Volunteer hasnah = new Volunteer("hasnah", "Hasnah", "Said");
		Volunteer tuan = new Volunteer("tuan", "Tuan", "Dinh");
		
		myJobs = new JobMap();
		myUsers = new UserMap();
		
		myUsers.addUser(matt);
		myUsers.addUser(brook);
		myUsers.addUser(hasnah);
		myUsers.addUser(tuan);
		
		Job job1 = new Job (LocalDate.of(2018, 02, 24), 
				LocalDate.of(2018, 02, 24), 
				"Seward Park", matt, 
				"5900 Lake Washington Blvd S, Seattle, WA 98118",
				"Removing invasive species and covering the site with burlap and mulch");
		matt.createJob(job1);

			
		Job job2 = new Job (LocalDate.of(2018, 02, 25) 
				, LocalDate.of(2018, 02, 26)
				, "Discovery Park", brook 
				, "3801 Discovery Park Blvd, Seattle, WA 98199" 
				, "Replacing playground equipments");
		
		brook.createJob(job2);
		
		Job job3 = new Job(LocalDate.of(2018, 02, 24)
				, LocalDate.of(2018, 02, 26)
				, "Gas Works Park", matt
				, "2101 N Northlake Way, Seattle, WA 98103"
				, "Replacing playground equipments");
		
		matt.createJob(job3);
		
		Job job4 = new Job(LocalDate.of(2018, 02, 17)
				, LocalDate.of(2018, 02, 19)
				, "Lincoln Park", brook
				, "8011 Fauntleroy Way SW, Seattle, WA 98136"
				, "Planting native plants");
		brook.createJob(job4);
		
		Job job5 = new Job(LocalDate.of(2018, 02, 18)
				, LocalDate.of(2018, 02, 20)
				, "Volunteer Park", matt
				, "1247 15th Ave E, Seattle, WA 98112"
				, "Restoring and beautifying the landscape of the park");
		matt.createJob(job5);
		
		
		
		Job job6 = new Job (LocalDate.of(2018, 02, 19), 
				LocalDate.of(2018, 02, 20), 
				"Crown Hill Park", matt, 
				"9089 Holman Rd NW, Seattle, WA 98117",
				"Park grounds cleanup");
		matt.createJob(job6);

			
		Job job7 = new Job (LocalDate.of(2018, 02, 19) 
				, LocalDate.of(2018, 02, 21)
				, "Carkeek Park", matt 
				, "950 NW Carkeek Park Rd, Seattle, WA 98177"
				, "Removing non-native invasive plants");	
		matt.createJob(job7);
		
		
		
		Job job8 = new Job(LocalDate.of(2018, 03, 01)
				, LocalDate.of(2018, 03, 03)
				, "Salmon Bay Park", brook
				, "2001 NW Canoe Pl, Seattle, WA 98117"
				, "Setting up and tearing down stage");
		brook.createJob(job8);
		
		
		Job job9 = new Job(LocalDate.of(2018, 03, 01)
				, LocalDate.of(2018, 03, 02)
				, "Sunset Hill Park", brook
				, "7531 34th Ave NW, Seattle, WA 98117"
				, "Planting native plants");
		brook.createJob(job9);
		
		
		
		Job job10 = new Job(LocalDate.of(2018, 03, 04)
				, LocalDate.of(2018, 03, 05)
				, "Woodland Park", matt
				, "5500 Phinney Ave N, Seattle, WA 98103"
				, "Planting and mulching flowerbeds");
		matt.createJob(job10);
		
		
		
		Job job11 = new Job (LocalDate.of(2018, 03, 06), 
				LocalDate.of(2018, 03, 07), 
				"Kerry Park", matt, 
				"211 W Highland Dr, Seattle, WA 98119",
				"Weeding and mulching flowerbeds");
		matt.createJob(job11);

			
		Job job12 = new Job (LocalDate.of(2018, 03, 8) 
				, LocalDate.of(2018, 03, 8)
				, "Greenwood Park", brook 
				, "8905 Fremont Ave N, Seattle, WA 98103" 
				, "Resurfacing the limestone running trail");
		
		brook.createJob(job12);
		
		Job job13 = new Job(LocalDate.of(2018, 03, 8)
				, LocalDate.of(2018, 03, 8)
				, "Ballard Commons Park", matt
				, "5701 22nd Ave NW, Seattle, WA 98107"
				, "Park grounds cleanup");
		
		matt.createJob(job13);
		
		
		Job job14 = new Job(LocalDate.of(2018, 03, 13)
				, LocalDate.of(2018, 03, 14)
				, "Volunteer Park", matt
				, "1247 15th Ave E, Seattle, WA 98112"
				, "Restore and beautify the landscape of the park");
		matt.createJob(job14);
		
		
		
		Job job15 = new Job (LocalDate.of(2018, 03, 15), 
				LocalDate.of(2018, 03, 16), 
				"Kerry Park", matt, 
				"211 W Highland Dr, Seattle, WA 98119",
				"Weeding and mulching flowerbeds");
		matt.createJob(job15);

			
		Job job16 = new Job (LocalDate.of(2018, 03, 16) 
				, LocalDate.of(2018, 03, 18)
				, "Clark Lake Park", brook 
				, "12520 SE 240th St, Kent, WA 98031" 
				, "Monitoring and crowd flow management at upcoming event");
		
		brook.createJob(job16);
		
		Job job17 = new Job(LocalDate.of(2018, 03, 18)
				, LocalDate.of(2018, 03, 19)
				, "Lake Meridian Park", matt
				, "14800 SE 272nd St, Kent, WA 98030"
				, "Park grounds cleanup");
		
		matt.createJob(job17);
		
		
		Job job18 = new Job(LocalDate.of(2018, 04, 01)
				, LocalDate.of(2018, 04, 03)
				, "Jefferson Park", brook
				, "3801 Beacon Ave S, Seattle, WA 98108"
				, "Removing non-native invasive plants");
		brook.createJob(job18);
		
		Job job19 = new Job(LocalDate.of(2018, 04, 02)
				, LocalDate.of(2018, 04, 04)
				, "Dearborn Park", matt
				, "2919 S Brandon St, Seattle, WA 98108"
				, "Replacing playground equipments");
		matt.createJob(job19);
		
		
		
		
		myJobs.addJob(job1);
		myJobs.addJob(job2);
		myJobs.addJob(job3);
		myJobs.addJob(job4);
		myJobs.addJob(job5);
		myJobs.addJob(job6);
		myJobs.addJob(job7);
		myJobs.addJob(job8);
		myJobs.addJob(job9);
		myJobs.addJob(job10);
		myJobs.addJob(job11);
		myJobs.addJob(job12);
		myJobs.addJob(job13);
		myJobs.addJob(job14);
		myJobs.addJob(job15);
		myJobs.addJob(job16);
		myJobs.addJob(job17);
		myJobs.addJob(job18);
		myJobs.addJob(job19);
		
		myJobs.storeJobMap("UpcomingJobs.ser");		
		myUsers.storeUserMap("UsersInformations.ser");		

		
	}
	
	
	/*****************************************************************/
}
