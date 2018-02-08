package test;

import model.User;
import model.UserMap;

import org.junit.Before;
import org.junit.Test;
import model.ParkManager;
import model.Volunteer;

/**
 * Test class for UserMap, will be deleted when project finish.
 * @author Minqing Chen
 * @version 02/06/2018
 */
public class UserMapTest {

	private User matthew;
	
	private User hasnah;
	
	private User brook;
	
	private User tuan;
	
	/**a UserMap that stores users.*/
	private UserMap myUserMap;

	
	@Before
	public void setup() {
		matthew = new ParkManager("Matthew_Chen", "Matthew", "Chen");
		hasnah = new Volunteer("Hasnah_Said", "Hasnah", "Said");
		brook = new ParkManager("Brook_Negussie", "Brook", "Negussie");
		tuan = new Volunteer("Tuan_Dinh", "Tuan", "Dinh");
		myUserMap = new UserMap();
		
	}
	
	/**
	 * test whether save user information.
	 */
	@Test
	public void writeUsermap_saveAllUserInformationInAtxtFile_createTheTxtFileWithAllInformation() {
		myUserMap.addUser(brook);
		myUserMap.addUser(hasnah);
		myUserMap.writeUsermap("userInformation.ser");
		myUserMap.viewAllUser();
	}
	
	/**
	 * test whether load user information from a file.
	 */
	@Test
	public void readUsermap_loadAllUserInformationFromAtxtFile_loadTheTxtFileWithAllInformatioIntoUsermapn() {
		myUserMap.readUsermap("userInformation.ser");
		myUserMap.viewAllUser();
		Volunteer v = (Volunteer) myUserMap.getUser("Hasnah_Said");
		System.out.println(v.toString());
	}
	
	/**
	 * Test whether return Matthew_Chen's information.
	 */
	@Test
	public void getUser_getMatthewParkManager_MatthewInformation() {
		myUserMap.addUser(matthew);
		System.out.println(" add matthew park manager to map\n" + myUserMap.getUser("Matthew_Chen").toString());
	}
	
	/**
	 * Test whether return Tuan_Dinh's information.
	 */
	@Test
	public void getUser_getTuanVolunteer_TuanInformation() {
		myUserMap.addUser(tuan);
		System.out.println(" add tuan volunteer to map\n" + myUserMap.getUser("Tuan_Dinh").toString());
	}
	
	
	/**
	 * Test whether park manager Matthew_Chen in the UserMap.
	 */
	@Test
	public void addUser_addMatthewParkManager_Matthew_ChenInTheUserMap() {
		myUserMap.addUser(matthew);
		myUserMap.viewAllUsername();
	}
	
	/**
	 * Test whether volunteer Hasnah_Said in the UserMap.
	 */
	@Test
	public void addUser_addHasnahVolunteer_Hasnah_SaidInTheUserMap() {
		myUserMap.addUser(hasnah);
		myUserMap.viewAllUsername();
	}
	
	/**
	 * Test whether park manager Brook_Negussi in the UserMap.
	 */
	@Test
	public void addUser_addBrookParkManager_Brook_NegussieInTheUserMap() {
		myUserMap.addUser(brook);
		myUserMap.viewAllUsername();
	}
	
	/**
	 * Test whether volunteer Tuan_Dinh in the UserMap.
	 */
	@Test
	public void addUser_addTuanVolunteer_Tuan_DinhInTheUserMap() {
		myUserMap.addUser(tuan);
		myUserMap.viewAllUsername();
	}

}
