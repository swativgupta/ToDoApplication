import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * This class contains fields for the Task
 * String taskId assigned to to all tasks to store unique Id 
 * String status for the task status 
 * String taskTitle to store the task title or description
 * String projectTitle to store the project title or description
 * String date for the dueDate of the task
 * int taskCount to keep track of total number of task in 
 * tasklist and is than used to generate task id
 */
public class Task implements Comparable<Task> {

	private String taskId;
	private String taskStatus;
	private String taskTitle;
	private String projectTitle;
	private String dueDate;
	private int taskCount;

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectDescription) {
		this.projectTitle = projectDescription;
	}

	
	public Task() {
	}
	//Constructor to create the Task Object
	public Task(String taskDescription, String dueDate, String taskStatus, int taskCount) {
		System.out.println("Inside the task constructor");
		this.taskTitle = taskDescription;
		this.taskId = taskDescription + generateId(taskCount);// Need to improve to get a unique Id
		this.taskStatus = taskStatus;
		this.dueDate = dueDate;
		System.out.println("Exit of Task constructor");

	}


	public Task(String taskId, String taskDescription, String taskStatus, String projectDescription, String dueDate) {

		this.taskId = taskId;
		this.taskTitle = taskDescription;
		this.taskStatus = taskStatus;
		this.projectTitle = projectDescription;
		this.dueDate = dueDate;
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

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskDescrption) {
		this.taskTitle = taskDescrption;
	}

	public String getTaskDescrptionId() {
		return taskId;
	}

	public void setTaskDescrptionId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * This class will generate taskID during creation.
	 * 
	 * @author Swati Gupta
	 * @param int taskCount
	 * @return boolean
	 */

	public int generateId(int taskCount) {

		System.out.println("Going to call to do initialization");

		taskCount = taskCount + 1;
		System.out.println("New task id is generated and it is " + taskCount);
		return taskCount;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskStatus=" + taskStatus + ", taskTitle=" + taskTitle
				+ ", projectTitle=" + projectTitle + ", dueDate=" + dueDate + "]";
	}

}
