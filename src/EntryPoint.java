import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * At Present This class acts as a controller class as well as view for the application where
 * user can select menus and accordingly the flow of the application is handeled
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */

public class EntryPoint {
	TaskOrganiser taskOrganiser = new TaskOrganiser();
	toDoInitializations todoinitialization = new toDoInitializations();
	
	
	
	/**
	 * contains all the valid command words for to do App
	 * along with a string.
	 * @author Swati
	 *
	 */
	public enum MainMenu
	{
	    
	   
		TODO("To Do List"), QUIT("quit"), HELP("help"),TASK ("Create Task"), UNKNOWN("?"),SAVE("Save And Quit"),REMOVETASK("Delete Task"),VIEWTASK ("View Task");
	    
	    private String mainMenuString;
	    
	    /**
	     * Initialise with the corresponding string value.
	     * @param mainMenuString The entered string.
	     */
	    MainMenu(String mainMenuString)
	    {
	        this.mainMenuString = mainMenuString;
	    }
	    
	    /**
	     * @return The entered value as a string.
	     */
	    public String toString()
	    {
	        return mainMenuString;
	    }
		
		}
		
	public void myMainMenuList(){
		
		for(MainMenu c : MainMenu.values())
	       {    
	           System.out.println("Main Menu is " + c + " and main menu string  is "
	               + c.toString());
	       }
	}
	
	/**
	 * At Present This class acts as a controller class as well as view for the application where
	 * user can select menus and accordingly the flow of the application is handled
	 * 
	 * @author  Swati Gupta
	 * @version 2018.09.27
	 */
	public void mainMenu() {
		myMainMenuList();
		
		todoinitialization.count=  todoinitialization.loadAllTasks().size() ;
		System.out.println("The value of count is set to "+todoinitialization.count);
	   Scanner reader = new Scanner(System.in);  
		 System.out.println("Enter a  menu: ");
		 String n = reader.nextLine();  
		// boolean wantToQuit = false;
	       switch (n) {
	            
	           
	            case "Create Task":
	            	System.out.println("You are about to create your tasks");
	            	System.out.println("Please enter a date (mm/dd/yyyy)");
	            	System.out.println("first parameter is task description,due date ,task status");
	                boolean val=taskOrganiser.createTask("First Task", "09/29/2018", "todo");
	            	System.out.println(val);
	                System.out.println("_______________________________.");
	                break;        
	       
	        }
	        
	    }
		 
}




