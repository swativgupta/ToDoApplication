package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Controller.TaskOrganiser;
import Utility.Constants;
import Utility.Validator;

/**
 * This class is the user interface for the application
 * 
 * 
 * @author Swati Gupta
 * @version 2018.09.27
 */

public class EntryPoint {
	TaskOrganiser taskOrganiser;
	Validator validate = new Validator();
	Constants constant;

	public EntryPoint() throws ClassNotFoundException, IOException, ParseException {
		this.taskOrganiser = new TaskOrganiser();
		this.constant = new Constants();
	}

	/**
	 * contains all the valid input words for to do App along with a string.
	 * 
	 * @author Swati
	 *
	 */
	public enum MainMenu {

		TASK(">> [1] Create Task"), TODO(">> [2] Show Task"), SortByDate(">> [3] Sort By date"),
		AssignProject(">> [4] Assign Project"), REMOVETASK(">> [5] Delete Task"), UPDATETASK(">> [6] Update Task"),
		FILTERBYPROJECT(">> [7] Filter By Project"), SAVE(">> [8] Save And Quit");

		private String mainMenuString;

		/**
		 * Initialise with the corresponding string value.
		 * 
		 * @param mainMenuString The entered string.
		 */
		MainMenu(String mainMenuString) {
			this.mainMenuString = mainMenuString;
		}

		/**
		 * @return The entered value as a string.
		 */
		public String toString() {
			return mainMenuString;
		}

	}

	/**
	 * Below method displays the menulist on the screen along with a string.
	 * 
	 * @author Swati
	 *
	 */

	public String myMainMenuList() {
		System.out.println(" ############################################ ");
		System.out.println(" >> Please pick an option number from below :: ");
		System.out.println(" ############################################ ");
		for (MainMenu c : MainMenu.values()) {

			System.out.println(c.toString());
		}
		System.out.println(" ############################################ ");
		String inputString = takeUserInput();
		return inputString;
	}

	/**
	 * This method controls the flow as per user input
	 * 
	 * @author Swati Gupta
	 * @throws ParseException
	 * 
	 */
	public void mainMenu() throws ParseException {

		boolean wantToQuit = true;
		System.out.println(">> Welcome to To do list ");
		System.out.println(">> You have " + taskOrganiser.sizeOfTaskList() + " tasks in task list and "
				+ taskOrganiser.calculateTaskDone() + " tasks are done! ");
		while (wantToQuit) {
			String inputString = myMainMenuList();
			switch (inputString) {
			case "1":
				System.out.println("To create your task please enter your fields one by one in below order");
				System.out.println(">> Task description , >> task status , >> due date (mm/dd/yyyy) ");

				String description = takeUserInput();

				String status = takeUserInput();
				String dateEntered = takeUserInput();

				// Validation for date

				boolean dateValid = true;

				Date date = null;
				while (dateValid) {
					if (dateEntered.equals("") || dateEntered.isEmpty()) {
						System.out.println("You have not entered a valid date.Please enter a valid date");
						dateEntered = takeUserInput();
					} else if (!dateEntered.equals("") && !dateEntered.isEmpty()) {
						date = validate.convertToDate(dateEntered);
						if (date != null) {
							dateValid = false;
						} else {
							System.out.println("You have not entered a valid date.Please enter a valid date");
							dateEntered = takeUserInput();
						}

					}

				}
				taskOrganiser.createTask(description, date, status);
				break;

			case "2":
				System.out.println(" #####***********TO DO LIST***********###### ");
				taskOrganiser.showTaskList();
				System.out.println(" ###***********TO DO LIST END***********### ");
				break;
			case "3":
				taskOrganiser.sortByDate();
				break;
			case "4":

				if (checkForTaskID().equalsIgnoreCase(Constants.YES)) {

					System.out.println("Please enter taskid of Task for which you want to assign project to");
					String taskId = takeUserInput();

					if (taskOrganiser.taskExist(taskId)) {
						System.out.println("Found a task with provided Id");
						String inputValue = takeUserInput();

						taskOrganiser.updatetask(taskId, Constants.PROJECT_TITLE, inputValue);

					} else {

						System.out.println("No project is assigned");
					}
				} else {

					System.out.println("Type any other string to exit and return to main menu");
				}
				break;
			case "5":
				if (checkForTaskID().equalsIgnoreCase(Constants.YES)) {
					System.out.println("Please enter taskid of Task for which you want to remove");
					taskOrganiser.removeTask(takeUserInput());
					taskOrganiser.showTaskList();
				} else {

					System.out.println("Type any other string to exit and return to main menu");
				}
				break;
			case "6":
				if (checkForTaskID().equalsIgnoreCase(Constants.YES)) {
					System.out.println("Please enter taskid of Task for which you want to update");

					updateTask(takeUserInput());
				} else {

					System.out.println("Type any other string to exit and return to main menu");
				}
				break;

			case "7":
				if (checkForProject().equalsIgnoreCase(Constants.YES)) {
					System.out.println("Please enter project for which you want to filter");
					String project = takeUserInput();
					taskOrganiser.filterByProject(project);
				} else {

					System.out.println("Type any other string to exit and return to main menu");
				}

				break;
			case "8":
				taskOrganiser.save();
				wantToQuit = false;
				break;

			}
		}

	}

	/**
	 * Below enum will display the sub menu that is task fields on the screen
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 */

	public enum SubMenu {
		dueDate(">> [1] Due Date"), taskTitle(">> [2] Task Title"), taskStatus(">> [3] Task Status"),
		Project(">> [4] Project"), QUIT(">> [5] Return To main Task List menu");

		private String subMenu;

		/**
		 * Initialise with the corresponding string value.
		 * 
		 * @param subMenu The entered string.
		 */
		SubMenu(String subMenu) {
			this.subMenu = subMenu;
		}

		/**
		 * @return The entered value as a string.
		 */
		public String toString() {
			return subMenu;
		}

	}

	/**
	 * This method will update the Task field entered by user
	 * 
	 * @author Swati Gupta
	 * @throws ParseException
	 * 
	 */
	public String mySubMenu() {

		System.out.println(" ############################################ ");
		System.out.println(" >> Please pick an option from below :: ");
		System.out.println(" ############################################ ");
		for (SubMenu c : SubMenu.values()) {

			System.out.println(c.toString());
		}
		System.out.println(" ############################################ ");
		String inputString = takeUserInput();
		return inputString;
	}

	/**
	 * Below method will display the task fields to help user to choose from
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 * @throws ParseException
	 */

	public void showTaskDetailMenu(String taskId) throws ParseException {
		System.out.println("Please enter the field that you want to update for task Id " + taskId);

		boolean wantToQuit = true;
		while (wantToQuit) {
			String inputString = mySubMenu();

			switch (inputString) {

			case "1":
				System.out.println("Please enter date to be updated and format is mm/dd/yyyy");
				String dateEntered = takeUserInput();

				/*
				 * if (!dateEntered.equals("") && !dateEntered.isEmpty()) {
				 * taskOrganiser.updatetask(taskId, Constants.DATE, dateEntered); } else {
				 * 
				 * SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
				 * sdf.setLenient(false); Date dueDate = sdf.parse("00/00/0000");
				 * 
				 * taskOrganiser.updatetask(taskId, Constants.DATE, dateEntered); }
				 */

				boolean dateValid = true;

				Date date = null;
				while (dateValid) {
					if (dateEntered.equals("") || dateEntered.isEmpty()) {
						System.out.println("You have not entered a valid date.Please enter a valid date");
						dateEntered = takeUserInput();
					} else if (!dateEntered.equals("") && !dateEntered.isEmpty()) {
						date = validate.convertToDate(dateEntered);
						if (date != null) {
							dateValid = false;
						} else {
							System.out.println("You have not entered a valid date.Please enter a valid date");
							dateEntered = takeUserInput();
						}

					}
				}
				taskOrganiser.updatetask(taskId, Constants.DATE, dateEntered);

				break;

			case "2":
				System.out.println("Please enter task description to be updated");

				String taskTitle = takeUserInput();

				taskOrganiser.updatetask(taskId, Constants.TASK_TITLE, taskTitle);

				break;
			case "3":
				System.out.println("Please enter task status to be updated");

				String taskStatus = takeUserInput();

				taskOrganiser.updatetask(taskId, Constants.TASK_STATUS, taskStatus);

				break;
			case "4":
				System.out.println("Please enter Project to be updated");
				String projectTitle = takeUserInput();

				taskOrganiser.updatetask(taskId, Constants.PROJECT_TITLE, projectTitle);

				break;
			case "5":
				wantToQuit = false;

				break;

			default:
				System.out.println("Not a valid Choice.Please enter a valid field from below list");

				break;

			}
		}

	}

	/**
	 * This method will fetch the task for user to be updated and then display the
	 * task fields to allow user to update any one of them
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 * @throws ParseException
	 */

	public void updateTask(String taskID) throws ParseException {
		System.out.println("Task to be updated is :: " + taskID);
		// Validate if task exists

		if (taskOrganiser.taskExist(taskID)) {

			showTaskDetailMenu(taskID);

		} else {

			System.out.println("No task updated");
		}
	}

	/**
	 * Below method is a utility that takes user input
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 */

	public String takeUserInput() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a  value: ");
		String n = reader.nextLine();
		return n;
	}
	
	/**
	 * Below method for user so that he/she can proceed if he/she knows taskId or just return back
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 */

	public String checkForTaskID() {
		System.out.println("If you know the task Id of task you want to edit or removet type Y  ");

		String checkProceed = takeUserInput();

		return checkProceed;
	}
	/**
	 * Below method for user so that he/she can proceed if he/she knows Project or just return back
	 * 
	 * @author Swati Gupta
	 * @version 2018.09.27
	 */

	public String checkForProject() {
		System.out.println("If you know the project title please type Y  ");

		String checkProceed = takeUserInput();

		return checkProceed;
	}

}
