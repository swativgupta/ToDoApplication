import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Task Organiser class organises all the Tasks all the functions to manage task
 * will be implimented here like show tasks,create task,Sort them by date and so
 * on
 * 
 * @author Swati Gupta
 * @version 2018.09.27
 */
public class TaskOrganiser {

	Validation validate;
	Writer writeCsv;
	Reader reader;
	ArrayList<Task> tasks;

	public TaskOrganiser() throws ClassNotFoundException, IOException, ParseException {
		this.tasks = this.load("ToDo.csv");
		validate = new Validation();
		reader = new Reader();

	}

	public int sizeOfTaskList() {

		//System.out.println("Total tasks in your to do list are " + tasks.size());
		return tasks.size();

	}

	/**
	 * Creates valid task.
	 * 
	 * @author Swati Gupta
	 * @pram taskDescription
	 * @pram dueDate
	 * @pram taskStatus
	 * @return boolean
	 */

	public boolean createTask(String taskDescription, String dueDate, String taskStatus) throws ParseException {

		
		if (validate.validateDate(dueDate) != null) {
			Task task = new Task(taskDescription, dueDate, taskStatus, tasks.size());
			tasks.add(task);
			return true;
		} else {
			System.out.println("Task is NOT CREATED .");
			return false;
		}
		
	}

	/**
	 * Shows all the tasks
	 * 
	 * @author Swati Gupta
	 * 
	 */
	public ArrayList<Task> showTaskList() {

		
		for (Task todo : tasks) {

			System.out.println(todo.toString());

		}

		return tasks;
	}

	/**
	 * Sort all the tasks by date
	 * 
	 * @author Swati Gupta
	 * 
	 */
	// add changes

	public void sortByDate() {
		Collections.sort(tasks);
		System.out.println("Total tasks in your to do list are " + tasks.size());
		for (Task todo : tasks) {

			System.out.println(todo.toString());

		}

	}
	/**
	 * Update the task for specified task Id in parameter
	 * 
	 * @author Swati Gupta
	 * 
	 * @return int task done
	 */
	public int calculateTaskDone() {
		int doneTask = 0;
		Collections.sort(tasks);
//		System.out.println("Total tasks in your to do list are " + tasks.size());

		for (Task task : tasks) {
			if (task.getTaskStatus().equalsIgnoreCase("Done")) {

				doneTask += doneTask;
			}

		}
		return doneTask;

	}

	/**
	 * Update the task for specified task Id in parameter
	 * 
	 * @author Swati Gupta
	 * @pram String taskID
	 * @pram String field
	 * @pram String value
	 * @return boolean status
	 */
	public boolean updatetask(String taskId, String field, String value) {
		Task task = fetchTaskById(taskId);
		boolean updateStatus = false;
		if (task != null) {
			if (field.equalsIgnoreCase("date")) {
				

				
				
				if (validate.validateDate(value) != null) {
					task.setDueDate(value);
					updateStatus= true;
				} else {
					System.out.println("Date is not a valid date the date format is mm/dd/yyyy and only future date is allowed.");
					updateStatus= false;
				}
				
				
			} else if (field.equalsIgnoreCase("taskTitle")) {
				task.setTaskTitle(value);
				updateStatus= true;
			} else if (field.equalsIgnoreCase("taskStatus")) {
				task.setTaskStatus(value);
				updateStatus= true;
			} else if (field.equalsIgnoreCase("projectTitle")) {
				task.setProjectTitle(value);
				updateStatus= true;
			}

		} else {

			System.out.println("There is no task for given task Id");
			updateStatus= false;
		}
		System.out.println("The Task is updated as below");
		System.out.println(task.toString());
		return updateStatus;
	}

	/**
	 * Delete the task for specified task Id in parameter
	 * 
	 * @author Swati Gupta
	 * @pram String taskID
	 * @return boolean status
	 */

	public boolean removeTask(String taskID) {
		System.out.println("You are about to remove the task with task ID :: " + taskID);
		boolean status = false;
		if (fetchTaskById(taskID) != null) {
			Task task = fetchTaskById(taskID);

			status = tasks.remove(task);

		} else {

			System.out.println("No Task Deleted");
		}
		System.out.println("Task deletion status for the task with task ID is :: " + taskID);
		return status;

	}

	/**
	 * Utility function to fetch the task for given task ID
	 * 
	 * @author Swati Gupta
	 * @pram String taskID
	 * @return Task Object
	 */

	public Task fetchTaskById(String taskID) {
		System.out.println("Fetching task for task Id :: " + taskID);
		Task taskForId = null;
		for (Task todo : tasks) {
			if (todo.getTaskDescrptionId().equals(taskID)) {

				taskForId = todo;
				//System.out.println("Fetched task for task Id :: " + taskID + " About to break");
				break;
			} else {
				//System.out.println("There is no task with task Id :: " + taskID);
				taskForId = null;
			}

		}
		return taskForId;
	}

	/**
	 * Utility function to check if task exists
	 * 
	 * @author Swati Gupta
	 * @pram String taskID
	 * @return Task Object
	 */

	public boolean taskExist(String taskID) {
		System.out.println("Searching task for task Id :: " + taskID);
		boolean taskExist = false;
		for (Task todo : tasks) {
			if (todo.getTaskDescrptionId().equals(taskID)) {
				taskExist = true;

				break;
			} else {
				System.out.println("There is no task with task Id :: " + taskID);
				taskExist = false;
			}

		}
		return taskExist;

	}

	/**
	 * This function saves the task list to a Repository which is file in this case
	 * 
	 * @author Swati Gupta
	 * @pram String taskID
	 * @return boolean
	 */
	public boolean save() {
		Writer w = new Writer();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("Task Id");
			sb.append(',');
			sb.append("Task description");
			sb.append(',');
			sb.append("Task Status");
			sb.append(',');
			sb.append("Project description");
			sb.append(',');
			sb.append("Due Date");
			sb.append('\n');
			for (Task task : tasks) {
				sb.append(task.getTaskDescrptionId());
				sb.append(',');
				sb.append(task.getTaskTitle());
				sb.append(',');
				sb.append(task.getTaskStatus());
				sb.append(',');
				sb.append(task.getProjectTitle());
				sb.append(',');
				sb.append(task.getDueDate());
				sb.append('\n');
			}
			w.writeToFile(sb.toString());
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * This function loads or reads the task list from a Repository which is file in
	 * this case
	 * 
	 * @author Swati Gupta
	 * @pram String filename
	 * @return Arraylist of tasks
	 */

	private ArrayList<Task> load(String filename) {
		Reader reader = new Reader();
		
		
		ArrayList<Task> tasks = reader.getTasks(filename);
		if (tasks.size() >= 1) {
			tasks.remove(0);
		}
		return tasks;
	}

	/**
	 * This function filters the task list by project
	 * 
	 * @author Swati Gupta
	 * @pram String project Name
	 * @return Arraylist of tasks
	 */

	public ArrayList<Task> filterByProject(String project) {
		ArrayList<Task> taskByProject = new ArrayList<Task>();
		//System.out.println("Filter by project called");
		Task task = null;
		for (Task todo : tasks) {
			if ((todo.getProjectTitle()!=null) && todo.getProjectTitle().equalsIgnoreCase(project)) {
				taskByProject.add(todo);

			}
		}
		if (taskByProject != null) {

			if (taskByProject != null && taskByProject.size() > 0) {
				for (Task todo : taskByProject) {

					System.out.println(todo.toString());

				}

			} else {

				System.out.println("There are no tasks for given project");
			}

			return taskByProject;
		} else {

			System.out.println("There is no task with Project Description " + project);
			return taskByProject;
		}

	}

}
