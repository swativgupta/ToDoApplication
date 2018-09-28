import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
/**
 * Task Organiser class organises all the Tasks all the functions to manage task will be implimented here
 * like show tasks,create task,Sort them by date and so on
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */
public class TaskOrganiser {
	
	Validation validate =new Validation();
	toDoInitializations todoinitialization = new toDoInitializations();
    protected void showTasks()
    {
    	
    	// Initialised the tasks by hard coded them need to retrieve them from repository and populate accordingly
    	
    Task task = new Task();
    Task otherTask = new Task();
    ArrayList <Task> todoTasks = new ArrayList<>();
       todoTasks.add(task);
       todoTasks.add(otherTask); 
       System.out.println("Total tasks in your to do list are "+todoTasks.size());
          for(Task todo:todoTasks) 
          {
    	    String status= todo.getTaskStatus();
    	     System.out.println("Task ::" +" status is :: " + status +" and description is :: "+
    	     todo.getTaskDescrption()+" and Id is  "+
    	     todo.getTaskDescrptionId()); 
    	  
           }
      
    }
/**
   * Creates valid task.
   * @author  Swati Gupta
   * @pram taskDescription
   * @pram dueDate
   * @pram taskStatus
   * @return boolean
   */
    
 public boolean createTask(String taskDescription,String dueDate ,String taskStatus) 
    {	
       if( validate.validateDate(dueDate)!=null)
       {
    	System.out.println("Inside create task after validation");
    	Task task = new Task(taskDescription,dueDate,taskStatus);
    	System.out.println("Task is created and going to add in task list");
    	todoinitialization.tasktList.add(task);
    	return true;
    	
       }
       else
       {
    	   System.out.println("Task is NOT CREATED and going to add in task list");
    	  return false; 
       }
    	
    }
 /**
  * Shows all the tasks
  * @author  Swati Gupta
  * 
  */
 public void showTaskList()
 {
 	
    System.out.println("Total tasks in your to do list are "+todoinitialization.count);
       for(Task todo:todoinitialization.tasktList) 
       {
 	    String status= todo.getTaskStatus();
 	    todo.toString();
 	     System.out.println("Task ::" +" status is :: " + status +" and description is :: "+
 	     todo.getTaskDescrption()+" and Id is  "+
 	     todo.getTaskDescrptionId()+ "  and dueDate is  "+
 	     todo.getDueDate()); 
 	  
        }
 }
       /**
        * Sort all the tasks by date
        * @author  Swati Gupta
        * 
        */
 //add changes
       public void sortByDate()
       {
    	   Collections.sort(todoinitialization.tasktList);
          System.out.println("Total tasks in your to do list are "+todoinitialization.count);
             for(Task todo:todoinitialization.tasktList) 
             {
       	    String status= todo.getTaskStatus();
       	    todo.toString();
       	     System.out.println("Task ::" +" status is :: " + status +" and description is :: "+
       	     todo.getTaskDescrption()+" and Id is  "+
       	     todo.getTaskDescrptionId()+ "  and dueDate is  "+
       	     todo.getDueDate()); 
       	  
              }
   
 }
 
 }
