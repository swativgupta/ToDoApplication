package Controller;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import Reader.Reader;
import Utility.Constants;
import Utility.Validator;
import Writer.Writer;
import model.Task;

/* organises all the Tasks all the functions to manage task
 * will be implimented here like show tasks,create task,Sort them by date and so
 * on
 * 
 * @author Swati Gupta
 * @version 2018.09.27
 */
public class TaskOrganiser {

	Validator validate;
	Writer writeCsv;
	Reader reader;
	ArrayList<Task> tasks;

	public TaskOrganiser() throws ClassNotFoundException, IOException, ParseException {
		this.tasks = this.load(Constants.FILE_PATH);
		validate = new Validator();
		reader = new Reader();

	}

	/**
	 * Provides the size of Task List.
	 * 
	 * @author Swati Gupta
	 * 
	 * @return int (Total number of tasks)
	 */

	public int sizeOfTaskList() {

		if (tasks != null && !tasks.isEmpty()) {
			return tasks.size();
		} else {

			return 0;
		}
	}

	/**
	 * Creates valid task.
	 * 
	 * @author Swati Gupta
	 * @pram taskDescription (It is Task title)
	 * @pram dueDate (Due date is mandatory)
	 * @pram taskStatus (It is Task Status)
	 * @return boolean (returns true if task is created)
	 */

	public boolean createTask(String taskDescription, Date dueDate, String taskStatus) throws ParseException {

		if (dueDate != null) {
			Task task = new Task(taskDescription, dueDate, taskStatus, tasks.size());
			tasks.add(task);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Shows all the tasks
	 * 
	 * @author Swati Gupta
	 * @return ArrayList of tasks
	 */
	public ArrayList<Task> showTaskList() throws NullPointerException {

		System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n", Constants.TASK_ID, Constants.TASK_STATUS,
				Constants.DUE_DATE.toString(), Constants.PROJECT_TITLE, Constants.TASK_TITLE);

		for (Task t : tasks) {
			/*
			 * System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n",
			 * t.getTaskDescrptionId(), t.getTaskStatus(), t.getDueDate().toString(),
			 * t.getProjectTitle(), t.getTaskTitle());
			 */
			System.out.println(t.toString());

		}

		return tasks;
	}

	/**
	 * Sort all the tasks by date
	 * 
	 * @author Swati Gupta
	 * @return ArrayList of tasks which are sorted
	 */

	public ArrayList<Task> sortByDate() {
		Collections.sort(tasks);
		System.out.println("Total tasks in your to do list are " + tasks.size());
		System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n", Constants.TASK_ID, Constants.TASK_STATUS,
				Constants.DUE_DATE.toString(), Constants.PROJECT_TITLE, Constants.TASK_TITLE);

		for (Task t : tasks) {

			/*
			 * System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n",
			 * t.getTaskDescrptionId(), t.getTaskStatus(), t.getDueDate().toString(),
			 * t.getProjectTitle(), t.getTaskTitle());
			 */
			System.out.println(t.toString());

		}
		return tasks;
	}

	/**
	 * Calculates Task for which status is Done
	 * 
	 * @author Swati Gupta
	 * 
	 * @return int tasks with status as done
	 */
	public int calculateTaskDone() {
		int doneTask = 0;

		// System.out.println("Total tasks in your to do list are " + tasks.size());

		for (Task task : tasks) {
			if (task.getTaskStatus().equalsIgnoreCase(Constants.DONE_STATUS)) {

				doneTask += doneTask;
			}

		}
		return doneTask;

	}

	/**
	 * Update the task for specified task Id in parameter
	 * 
	 * @author Swati Gupta
	 * @pram String taskID is system generated
	 * @pram String field to identify which task field has to be updated (Task
	 *       Title,Task status,project title,Date )
	 * @pram String values that user have entered to update
	 * @return boolean status returns true if task is updated
	 * @throws ParseException
	 */
	public Task updatetask(String taskId, String field, String value) throws ParseException {
		Task task = fetchTaskById(taskId);
		// boolean updateStatus = false;
		if (task != null) {
			if (field.equalsIgnoreCase(Constants.DATE)) {
				Date date = validate.convertToDate(value);
				task.setDueDate(date);
				/*
				 * if (validate.validateDueDate(date)) { task.setDueDate(date); // updateStatus
				 * = true; } else { System.out.println(
				 * "Date is not a valid date the date format is mm/dd/yyyy and only future date is allowed."
				 * ); // updateStatus = false; }
				 */

			} else if (field.equalsIgnoreCase(Constants.TASK_TITLE)) {
				task.setTaskTitle(value);
				// updateStatus = true;
			} else if (field.equalsIgnoreCase(Constants.TASK_STATUS)) {
				task.setTaskStatus(value);
				// updateStatus = true;
			} else if (field.equalsIgnoreCase(Constants.PROJECT_TITLE)) {
				task.setProjectTitle(value);
				// updateStatus = true;
			}

		} else {

			System.out.println("There is no task for given task Id");
			// updateStatus = false;
		}
		System.out.println("The Task is updated as below");
		System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n", Constants.TASK_ID, Constants.TASK_STATUS,
				Constants.DUE_DATE.toString(), Constants.PROJECT_TITLE, Constants.TASK_TITLE);
		System.out.println(task.toString());
		return task;
	}

	/**
	 * Delete the task for specified task Id in parameter
	 * 
	 * @author Swati Gupta
	 * @pram String taskID to identify which task has to be updated
	 * @return boolean status returns true if task is deleted
	 */

	public boolean removeTask(String taskID) {
		System.out.println("Removing the task with task ID :: " + taskID);
		boolean status = false;
		if (taskID != null && !taskID.equals("")) {
			Task task = fetchTaskById(taskID);
			if (task != null) {

				status = tasks.remove(task);
				System.out.println(">>> Task Successfully Deleted");

			} else {

				System.out.println(">>> No Task Deleted");
			}
			// System.out.println("Task deletion status for the task with task ID is :: " +
			// taskID);
		}
		return status;

	}

	/**
	 * Delete all the tasks added for testing purpose
	 * 
	 * @author Swati Gupta
	 *
	 * @return boolean status true if all tasks are deleted
	 */

	public boolean removeAllTask() {
		System.out.println("Removing all tasks  ");
		boolean status = false;
		if (tasks.size() > 0) {
			tasks.clear();
		} else {

			System.out.println("tasklist is empty");
			return true;
		}
		return status;

	}

	/**
	 * Utility function to fetch the task for given task ID
	 * 
	 * @author Swati Gupta
	 * @pram String taskID to fetch the task of specified ID
	 * @return Task Object
	 */

	public Task fetchTaskById(String taskID) {
		System.out.println("Fetching task for task Id :: " + taskID);
		Task taskForId = null;
		for (Task todo : tasks) {
			if (todo.getTaskDescrptionId().equals(taskID)) {

				taskForId = todo;
				// System.out.println("Fetched task for task Id :: " + taskID + " About to
				// break");
				break;
			} else {
				// System.out.println("There is no task with task Id :: " + taskID);
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
				//System.out.println("There is no task with task Id :: " + taskID);
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
	 * @return boolean true if saved successfully
	 */
	public boolean save() {
		Writer w = new Writer();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append(Constants.TASK_ID);
			sb.append(Constants.COMMA);
			sb.append(Constants.TASK_TITLE);
			sb.append(Constants.COMMA);
			sb.append(Constants.TASK_STATUS);
			sb.append(Constants.COMMA);
			sb.append(Constants.PROJECT_TITLE);
			sb.append(Constants.COMMA);
			sb.append(Constants.DUE_DATE);
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
				sb.append(dateToString(task.getDueDate()));
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
	 * This function converts Date object to strong before saving to File so that it
	 * saves date in mm/dd/yyyy format.
	 * 
	 * 
	 * @author Swati Gupta
	 * @pram Date date
	 * @return String date
	 */

	private String dateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		if (date != null) {
			String stringDate = df.format(date);
			return stringDate;
		} else {
			return null;
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

		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			tasks = reader.getTasks(filename);
			if (tasks.size() >= 1) {
				tasks.remove(0);
			}
			return tasks;
		} catch (Exception e) {
			System.out.println("Could not read from file,file is not in correct format");
			e.printStackTrace();
			return tasks;
		}

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
		// System.out.println("Filter by project called");
		Task task = null;
		for (Task todo : tasks) {
			if ((todo.getProjectTitle() != null) && todo.getProjectTitle().equalsIgnoreCase(project)) {
				taskByProject.add(todo);

			} else if (project== null || project.equals(null)) {

				if ((todo.getProjectTitle()== null)  || todo.getProjectTitle().equals(null)) {
					taskByProject.add(todo);
				}

			}
		}
		if (taskByProject != null) {

			if (taskByProject != null && taskByProject.size() > 0) {
				System.out.printf("%-20s %-20s  %-30s %-20s %-20s\n", Constants.TASK_ID, Constants.TASK_STATUS,
						Constants.DUE_DATE.toString(), Constants.PROJECT_TITLE, Constants.TASK_TITLE);
				for (Task t : taskByProject) {

					System.out.printf(t.toString());

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
