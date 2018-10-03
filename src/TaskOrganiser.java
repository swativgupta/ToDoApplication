import java.io.FileNotFoundException;
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
	
	UserInput userInput =new UserInput();
	Validation validate =new Validation();
	WritingToCSV writeCsv = new WritingToCSV();
	
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
    	toDoInitializations.tasktList.add(task);
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
 	
    System.out.println("Total tasks in your to do list are "+toDoInitializations.count);
       for(Task todo:toDoInitializations.tasktList) 
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
    	   Collections.sort(toDoInitializations.tasktList);
          System.out.println("Total tasks in your to do list are "+toDoInitializations.count);
             for(Task todo:toDoInitializations.tasktList) 
             {
       	    String status= todo.getTaskStatus();
       	    todo.toString();
       	     System.out.println("Task ::" +" status is :: " + status +" and description is :: "+
       	     todo.getTaskDescrption()+" and Id is  "+
       	     todo.getTaskDescrptionId()+ "  and dueDate is  "+
       	     todo.getDueDate()); 
       	  
              }
   
 }
	public void assignProject(String taskID) {
		System.out.println("Your  task Id is "+taskID +"and you are going to assign a project to it");
		
		if(fetchTaskById(taskID)!=null)
		{
		Task task=fetchTaskById(taskID);
				
	//			updateTask(task.getTaskDescrptionId());
		
			
		}else {
			
			System.out.println("Please enter a valid Task ID");
		}
		
	}
	
	/**
     * Update the task for specified task Id in parameter
     * @author  Swati Gupta
     * @pram String taskID
     * @return boolean status
     */
	
	
	
	/**
     * Delete the task for specified task Id in parameter
     * @author  Swati Gupta
     * @pram String taskID
     * @return boolean status
     */
	
	
	public boolean removeTask(String taskID) {
		System.out.println("You are about to remove the task with task ID :: " +taskID);
		boolean status = false;
		if(fetchTaskById(taskID)!=null)
		{
		Task task=fetchTaskById(taskID);
				
		status=toDoInitializations.tasktList.remove(task);
		
			
		}else {
			
			System.out.println("No Task Deleted");	
		}
		System.out.println("Task deletion status for the task with task ID is :: " +taskID);
		return status;
		
	}
	
	/**
     * Utility function to fetch the task for given task ID
     * @author  Swati Gupta
     * @pram String taskID
     * @return Task Object
     */
	
	
	public Task fetchTaskById(String taskID) {
		System.out.println("Fetching task for task Id :: " + taskID);
		Task taskForId=null;
		for(Task todo:toDoInitializations.tasktList) {
			if(todo.getTaskDescrptionId().equals(taskID)) {
				
				taskForId= todo;
				System.out.println("Fetched task for task Id :: " + taskID +" About to break");
				break;
			}else {
		System.out.println("There is no task with task Id :: " + taskID);		
				taskForId = null;
			}
			
		}
		return taskForId;
		
		
	}
	public void updateTask(Task task, String feild) {
		switch (feild) {
        
		case "Due Date":
			System.out.println("Please enter date to be updated");
			task.setDueDate(userInput.takeInput());
			break;
        case "Task Description": 
        	System.out.println("Please enter task description to be updated");
			task.setTaskDescrption(userInput.takeInput());
			break;
        case "Task Status": 
        	System.out.println("Please enter task status to be updated");
			task.setTaskStatus(userInput.takeInput());
			break;
        case "Project": 
        	System.out.println("Please enter Project to be updated");
        	String inputValue=userInput.takeInput();
			task.setProjectDescription(inputValue);
			break;
		
	}
		System.out.println(task.toString());
		
	}
	public void saveToFile() {
		
		try {
			writeCsv.writeToFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 }
