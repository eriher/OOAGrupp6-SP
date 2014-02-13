import model.ClientNode;
import model.FileManagement;

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

	private static final int PORT = 4444;

	public static void main(String[] args) {
		// ClientNode node = ClientNode.singleton(PORT); //TODO atm recieves
		// input and prints out the string, its nasty right now I know sry...
		FileManagement filetest = new FileManagement();
		System.out.println(filetest.checkPassword("test.txt", "124456789876"));

	}

}
