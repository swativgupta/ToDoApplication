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
	Validation validate =new Validation();
	UserInput uInput = new UserInput();
	
	
	/**
	 * contains all the valid command words for to do App
	 * along with a string.
	 * @author Swati
	 *
	 */
	public enum MainMenu
	{
	   
		TASK ("[1] Create Task"),TODO("[2] Show Task"),SortByDate ("[3] Sort By date"),AssignProject("[4] Assign Project"),REMOVETASK("[5] Delete Task"),
		UPDATETASK("[6] Update Task"),SAVE("[7] Save And Quit"), QUIT("[8] quit"), HELP("[9] help");
	    
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
	           System.out.println(" Please enter a number from the below menu "
	               + c.toString());
	       }
	}
	public String takeUserInput() {
		Scanner reader = new Scanner(System.in);  
		 System.out.println("Enter a  value: ");
		 String n = reader.nextLine();
		return n;
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
		
		String n=takeUserInput();
		System.out.println("The value of count is set to "+toDoInitializations.count);
	      
		// boolean wantToQuit = false;
	       switch (n) {
	            case "1":
	            	System.out.println("You are about to create your tasks");
	            	System.out.println("Date Format is (mm/dd/yyyy)");
	            	System.out.println("Parameters are task description,due date ,task status");
	            	String description=takeUserInput();
	            	String date=takeUserInput();
	            	if( validate.validateDate(date)!=null) {
	            		
	            		
	            	}
	            	String status=takeUserInput();
	            	//09/29/2018
	                boolean val=taskOrganiser.createTask(description, date, status);
	            	//System.out.println(val);	
	                System.out.println("_______________________________.");
	                System.out.println("Returning to the main menu");
	                myMainMenuList();
	                takeUserInput();
	                break; 
	            case "2":
	            	 taskOrganiser.showTaskList();
	            	 System.out.println("Returning to the main menu");
	            	 myMainMenuList();
		             takeUserInput();
	            	 break; 
	            case "3":
	            	 taskOrganiser.sortByDate();
	            	 myMainMenuList();
		             takeUserInput();
	            	 break; 
	            case "4":
	            	 updateTask("task33","Project");
	            	 myMainMenuList();
		             takeUserInput();
	            	 break; 
	            case "5":
	            	 taskOrganiser.removeTask("Some Task");
	            	 taskOrganiser.showTaskList();
	            	 myMainMenuList();
		             takeUserInput();
	            	 break; 	 	 
	            case "6":    	
	            	 updateTask("task33","null");
	            	 myMainMenuList();
		             takeUserInput();
	            	 break; 
	            case "7":    	
	            	taskOrganiser.saveToFile();
	            	myMainMenuList();
		            takeUserInput();
	            	 break; 
	            case "8":    	
	            	//quit
	            	myMainMenuList();
		             takeUserInput();
	            	 break;
	            case "9":    	
	            //	Help
	            	 break;
	            default: 
	            	System.out.println("Not a valid Choice.Please enter a valid number from below list");
	            	System.out.println("Returning to the main menu");
	            	myMainMenuList();
		             takeUserInput();
	            	 break;
	        
	        }
	        
	    }
	
	
	
	public enum SubMenu
	{
	    
	   
		dueDate("Due Date"), QUIT("quit"), HELP("help"),taskDescription ("Task Description"), UNKNOWN("?"),taskStatus("Task Status"),Project("Project");
	    
	    private String subMenu;
	    
	    /**
	     * Initialise with the corresponding string value.
	     * @param subMenu The entered string.
	     */
	    SubMenu(String subMenu)
	    {
	        this.subMenu = subMenu;
	    }
	    
	    /**
	     * @return The entered value as a string.
	     */
	    public String toString()
	    {
	        return subMenu;
	    }
		
		}
		
	public void mySubMenu(){
		
		for(SubMenu c : SubMenu.values())
	       {    
	           System.out.println("Sub Menu is " + c + " and sub menu string  is "
	               + c.toString());
	       }
	}
	
	
	
	
	

	public void showTaskDetailMenu(Task task) {
		System.out.println("Please enter the field that you want to update for task Id "+task.getTaskDescrptionId());
		mySubMenu();
		String menuEntered =uInput.takeInput();
		 switch (menuEntered) {
         
         case "Due Date": taskOrganiser.updateTask(task,"Due Date");
         chooseMenu();
         
         break;
         case "Task Description": taskOrganiser.updateTask(task,"Task Description");
         chooseMenu() ;
         break;
         case "Task Status": taskOrganiser.updateTask(task,"Task Status");
         chooseMenu() ;
         break;
         case "Project": taskOrganiser.updateTask(task,"Project");
         chooseMenu() ;
         break;
         default: 
         	System.out.println("Not a valid Choice.Please enter a valid field from below list");
         	System.out.println("Returning to the main menu");
         	mySubMenu();
         	takeUserInput();
         	 break;    
         
     }
		
	}
	
	
	private void chooseMenu() {
		System.out.println("Do you want to continue to update other field if yes please type Y or type N");
		String n=takeUserInput();
		
		if(n.equalsIgnoreCase("y") ){
			mySubMenu();
			takeUserInput();
		}else if(n.equalsIgnoreCase("n")){
			myMainMenuList();
            takeUserInput();
			
		}else {
			System.out.println("Please select a valid value which is  Y or type N");
			chooseMenu();
		}
		
	}
	public void updateTask(String taskID,String field) {
		System.out.println("Task to be updated is :: " +taskID);
		
		if(taskOrganiser.fetchTaskById(taskID)!=null)
		{
		Task task=taskOrganiser.fetchTaskById(taskID);
			
		System.out.println(task.toString());
		
		if(field!="Project") {
		showTaskDetailMenu(task);
		}else{
		
			taskOrganiser.updateTask(task,"Project");
			
		}
		System.out.println("Task with task ID is :: " +taskID +" updated");
			
		}else {
			
			System.out.println("No task updated");
		}
		
		
	}
	
	
		 
}




