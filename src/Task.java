import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
/**
 * This class is the data part of the Application and cotains 
 * all the getters and setters for the object
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */
public class Task implements Comparable<Task> {
	
	private String taskId;
    private String taskStatus;	
    private String taskDescrption;
    private String projectDescription ;
    private String dueDate;
   

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	static Integer count = 0;
    public Task(String taskDescription,String dueDate ,String taskStatus) 
    {
     	System.out.println("Inside the task constructor");
        this.taskDescrption = taskDescription;
        this.taskId=taskDescription+generateId();// Need to improve to get a unique Id
        this.taskStatus=taskStatus;
        this.dueDate=dueDate;
        System.out.println("Exit of Task constructor");
    	
    }
    
    public Task() 
    {
        this.taskDescrption = "Hard Code Description";
        this.taskId=taskDescrption+generateId();
        this.taskStatus="todo";
        this.dueDate=dueDate;
    }
    

	public Task(String taskId, String taskDescription, String taskStatus, String projectDescription, String dueDate) {
		
		 this.taskId=taskId;
		 this.taskDescrption = taskDescription;
	       
	        this.taskStatus=taskStatus;
	        this.projectDescription=projectDescription;
	        this.dueDate=dueDate;
	}

	@Override
	public int compareTo(Task task) {
		if (getDueDate() == null || task.getDueDate() == null)
		      return 0;
		    return getDueDate().compareTo(task.getDueDate());
	}
	
	
    
    public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		
		this.dueDate = dueDate;
	}
	public String getTaskDescrption() {
		return taskDescrption;
	}
	public void setTaskDescrption(String taskDescrption) {
		this.taskDescrption = taskDescrption;
	}
	public String getTaskDescrptionId() {
		return taskId;
	}
	public void setTaskDescrptionId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskStatus() 
	{
			return taskStatus;
	}
	public void setTaskStatus(String taskStatus) 
	{
			this.taskStatus = taskStatus;
	}
	/**
	   * This class will validate the task during creation.
	   * @author  Swati Gupta
	   * 
	   * @return boolean
	   */	
	
	public int generateId(){  
        
        System.out.println("Going to call to do initialization");
        
         count=count+1;
         System.out.println("New task id is generated and it is "+count); 
          return count;     
    }

	@Override
	public String toString() {
		return "Task [taskId=" + taskId
				+ ", taskStatus=" + taskStatus + ", taskDescrption=" + taskDescrption + ", projectDescription="
				+ projectDescription + ", dueDate=" + dueDate + "]";
	}
	
}
