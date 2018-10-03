import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Iterator;
/**This  is the entry point of to do list.
 * The application starts from here
 *  @author Swati Gupta
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntryPoint entry= new EntryPoint();
		//toDoInitializations.tasktList;
		toDoInitializations todoinitialization = new toDoInitializations();
		todoinitialization.tasktList=todoinitialization.loadAllTasks();
		//todoinitialization.count=  todoinitialization.loadAllTasks().size() ;
	    entry.mainMenu();
		
		
	}
}
