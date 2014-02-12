import model.ClientNode;

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
	  
	  
	  ClientNode node = ClientNode.singleton(PORT);	//TODO testar att skicka från communication
	  }



}
