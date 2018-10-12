package view;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**This  is the entry point of to do list.
 * The application starts from here
 *  @author Swati Gupta
 */

public class Main {

	/**
	 * This is the entry point of program and all initializations are done here
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException {
		EntryPoint entry= new EntryPoint();
			
	    entry.mainMenu();
		
		
	}
}
