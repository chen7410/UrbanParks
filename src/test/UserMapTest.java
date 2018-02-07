package test;

import model.User;
import model.UserMap;

import static org.junit.Assert.*;
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

	/**test user matthew.*/
	private User matthew;
	
	/**test user hasnah.*/
	private User hasnah;
	
	/**test user brook.*/
	private User brook;
	
	/**test user tuan.*/
	private User tuan;
	
	/**a UserMap that stores users.*/
	private UserMap myUserMap;
	
	/**
	 * initialize fields.
	 */
	@Before
	public void setup() {
		matthew = new ParkManager("Matthew_Chen", "Matthew", "Chen");
		hasnah = new Volunteer("Hasnah_Said", "Hasnah", "Said");
		brook = new ParkManager("Brook_Negussie", "Brook", "Negussie");
		tuan = new Volunteer("Tuan_Dinh", "Tuan", "Dinh");
		myUserMap = new UserMap();
		
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
		myUserMap.viewAllUser();
	}
	
	/**
	 * Test whether volunteer Hasnah_Said in the UserMap.
	 */
	@Test
	public void addUser_addHasnahVolunteer_Hasnah_SaidInTheUserMap() {
		myUserMap.addUser(hasnah);
		myUserMap.viewAllUser();
	}
	
	/**
	 * Test whether park manager Brook_Negussi in the UserMap.
	 */
	@Test
	public void addUser_addBrookParkManager_Brook_NegussieInTheUserMap() {
		myUserMap.addUser(brook);
		myUserMap.viewAllUser();
	}
	
	/**
	 * Test whether volunteer Tuan_Dinh in the UserMap.
	 */
	@Test
	public void addUser_addTuanVolunteer_Tuan_DinhInTheUserMap() {
		myUserMap.addUser(tuan);
		myUserMap.viewAllUser();
	}
	
	
	
	
}
