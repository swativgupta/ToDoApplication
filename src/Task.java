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
public class Task {
	
	private String taskDescrptionId;
    private String taskStatus;	
    private String taskDescrption;
    private String projectDescription ;
    private String dueDate;
    
    public Task(String taskDescription,String dueDate ,String taskStatus) 
    {
     	
        this.taskDescrption = taskDescrption;
        this.taskDescrptionId=taskDescrption+generateId();// Need to improve to get a unique Id
        this.taskStatus="taskStatus";
        this.dueDate=dueDate;
    	
    }
    
    public Task() 
    {
        this.taskDescrption = "Hard Code Description";
        this.taskDescrptionId=taskDescrption+generateId();
        this.taskStatus="todo";
        this.dueDate=dueDate;
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
		return taskDescrptionId;
	}
	public void setTaskDescrptionId(String taskDescrptionId) {
		this.taskDescrptionId = taskDescrptionId;
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
        int randomId = 0;    
        Random rand = new Random();     
        for (int j=0;j < 10;j++)    
       {       
           randomId = rand.nextInt();      
       }     
          return randomId;     
    }

	@Override
	public String toString() {
		return "Task [taskDescrptionId=" + taskDescrptionId
				+ ", taskStatus=" + taskStatus + ", taskDescrption=" + taskDescrption + ", projectDescription="
				+ projectDescription + ", dueDate=" + dueDate + "]";
	}
	
	
	
	
	
}
