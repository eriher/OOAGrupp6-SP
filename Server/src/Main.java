import model.FileManagement;
import controller.Workflow;

/**
 * Class Main contains starting point(main method) of the Server.
 * 
 * @author Henrik Johansson
 * @version 2013-02-12
 */
public class Main {	

	/**
	 * @param args
	 */


	public static void main(String[] args) {
		
		FileManagement fileMan = new FileManagement();
		
		
		
		
	/*	Users users = new Users();					//Write some users to the bin file
		users.add("a", "a", "Admin");
		users.add("e", "e", "Employee");
		users.add("199402219773","losen1994","King");
		users.add("f", "f", "wannabeHaxxor");
		fileMan.writeUsersFile("users.bin", users);*/
		
		

		/*Users users = fileMan.getUsersList();
		User user = users.getUser("a");
		
		System.out.println("PerNr: " + user.getPerNr() );
		
		System.out.println("Name: " + user.getName() );

		System.out.println("Password: "+ user.getPassword() );*/
		
		
		
		Workflow flow = new Workflow();		//TODO uncomment when done testing Users

	}

}
