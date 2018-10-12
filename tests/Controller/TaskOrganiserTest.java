/**
 * 
 */
package Controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Rule;
//import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.sun.source.util.TaskListener;

import Controller.TaskOrganiser;
import Utility.Constants;
import model.Task;

/**
 * @author tmp-sda-1167
 *
 */
class TaskOrganiserTest {
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
	TaskOrganiser to ;
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	
	@BeforeEach
	void setUp() throws Exception {
		to= new TaskOrganiser();
	}

	
		// Helper methods
	
	private void CreateTaskForTest() throws ParseException{
		to.removeAllTask();
		sdf.setLenient(false);
		Date dueDate1=sdf.parse("12/12/2018");
		to.createTask("First TASK", dueDate1, "Pending");
		
		
		sdf.setLenient(false);
		Date dueDate2=sdf.parse("12/12/2019");
		to.createTask("Second TASK", dueDate2, "Done");
		
		sdf.setLenient(false);
		Date dueDate3=sdf.parse("12/11/2019");
		to.createTask("Third TASK", dueDate3, "WIP");
		
		sdf.setLenient(false);
		Date dueDate4=sdf.parse("12/11/2018");
		to.createTask("Fourth TASK", null, "Done");
		
		sdf.setLenient(false);
		Date dueDate5=sdf.parse("12/11/3019");
		to.createTask("Fifth TASK", null, "Done");
	}
	
	private void UpdateTaskForTest() throws ParseException{
		CreateTaskForTest();
		
        ArrayList<Task> taskList= to.showTaskList();
        taskList.get(0).getTaskDescrptionId();
        taskList.get(1).getTaskDescrptionId();
        taskList.get(2).getTaskDescrptionId();
        taskList.get(3).getTaskDescrptionId();
        taskList.get(4).getTaskDescrptionId();
        
        Task task6=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.PROJECT_TITLE, null);
        task6=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.TASK_TITLE, "First task Updated");
	    
		Task task2=to.updatetask(taskList.get(1).getTaskDescrptionId(), Constants.PROJECT_TITLE, "Project title is updated");
		
		Task task3=to.updatetask(taskList.get(2).getTaskDescrptionId(), Constants.TASK_STATUS, "Status title is updated");
		Task task4=to.updatetask(taskList.get(2).getTaskDescrptionId(), Constants.PROJECT_TITLE, "Project title is updated");
		
		Task task5=to.updatetask(taskList.get(3).getTaskDescrptionId(), Constants.PROJECT_TITLE, "Project is updated");
		
		//Task task8=to.updatetask(taskList.get(4).getTaskDescrptionId(), Constants.PROJECT_TITLE, " ");
	}
	
	public Date convertToDate(String dueDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		sdf.setLenient(false);
		Date date = null;
		try {
			date = sdf.parse(dueDate);
		} catch (Exception e) {
			System.out.println("WRONG FORMAT DATE");
			date = null;
		}
		return date;
	}
	
	/**
	 * Test method for {@link TaskOrganiser#createTask(java.lang.String, java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testCreateTaskForOneCorrectTask() throws ClassNotFoundException, IOException, ParseException {
		
		String date="12/12/2018";
		
		sdf.setLenient(false);
		
		Date dueDate=sdf.parse(date);
		
		assertTrue(to.createTask("SomeTASK", dueDate, "Pending"));
	}
	/**
	 * Test method for {@link TaskOrganiser#createTask(java.lang.String, java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testCreateTaskFornullProject() throws ClassNotFoundException, IOException, ParseException {
		
		String date="12/12/2019";
		
		sdf.setLenient(false);
		
		Date dueDate=sdf.parse(date);
		
		assertTrue(to.createTask("SomeTASK", dueDate, null));
	}
	
	/**
	 * Test method for {@link TaskOrganiser#createTask(java.lang.String, java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testCreateTaskForEmptyProject() throws ClassNotFoundException, IOException, ParseException {
		
		String date="12/12/2019";
		
		sdf.setLenient(false);
		
		Date dueDate=sdf.parse(date);
		
		assertTrue(to.createTask("SomeTASK", dueDate, "  "));
	}
	
	
	@Test
	public void testCreateTaskForFuture() throws ClassNotFoundException, IOException, ParseException {
	String date="12/12/3019";
	sdf.setLenient(false);
	
	Date dueDate=sdf.parse(date);
	
	assertTrue(to.createTask("OtherTASK", dueDate, "Not Done"));
		
	}
	
	@Test
	public void testCreateTaskForCurrent() throws ClassNotFoundException, IOException, ParseException {
	
	
	Date dueDate=new Date();
	
	assertTrue(to.createTask("SomeTASK", dueDate, "Work in progress"));
		
	}

	@Test
	public void testCreateTaskOneTaskPreviousDate() throws ClassNotFoundException, IOException, ParseException {
		String date="12/12/2017";
		sdf.setLenient(false);
		Date dueDate=sdf.parse(date);
		
		assertTrue(to.createTask("SomeTASK", dueDate, "pending"));
		
	}
	
	@Test
	public void testCreateTaskwrongFormat() throws ClassNotFoundException, IOException, ParseException {
		String date = "12-12-2017";
		sdf.setLenient(false);
		
		Date dueDate=convertToDate(date);// This validation is done at front end
		
		assertFalse(to.createTask("SomeTASK", dueDate, "work in progress"));
		
	}

	/*@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testCreateTaskOneTaskWrongDateFormat() throws ClassNotFoundException, IOException, ParseException {
		String date="12-12-2019";
		sdf.setLenient(false);
		
		thrown.expect( Exception.class );
		thrown.expectMessage("Not a valid format");
		to.createTask("SomeTASK", sdf.parse(date), "PROJECT");
	}*/
	
	@Test
	public void testsortByDate() throws ParseException, ClassNotFoundException, IOException  {
		
	
		
		sdf.setLenient(false);
		
		Date dueDate=sdf.parse("12/11/2018");
		
		CreateTaskForTest();
		
		ArrayList<Task> taskList=to.sortByDate();
		Date date=taskList.get(0).getDueDate();
		assertEquals(true,date.equals(dueDate) );
		
		}
	// Tested for updates made in Arraylist when no task preExisted in files
	
	@Test
	public void testupdateTaskTitle() throws ParseException, ClassNotFoundException, IOException  {
		

		CreateTaskForTest();
		
		
		ArrayList<Task> taskList= to.showTaskList();
		
		Task task=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.TASK_TITLE, "taskTitle1");
		assertEquals("taskTitle1", task.getTaskTitle());
		
		Task task2 =to.updatetask(taskList.get(1).getTaskDescrptionId(), Constants.TASK_TITLE, "taskTitle2");
		assertEquals("taskTitle2", task2.getTaskTitle());
		}
	
	@Test
	public void testupdateDueDate() throws ParseException, ClassNotFoundException, IOException  {
		to.removeAllTask();
		
		sdf.setLenient(false);
		Date date=sdf.parse("11/23/6019");
		Date date1=sdf.parse( "11/23/9000");
		
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		
		Task task4=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.DATE, "11/23/6019");
		assertEquals(date, task4.getDueDate());
		
		
		
		Task task5 =to.updatetask(taskList.get(1).getTaskDescrptionId(), Constants.DATE, "11/23/9000");
		assertEquals(date1, task5.getDueDate());
		}
	@Test
	public void testupdateStatus() throws ParseException, ClassNotFoundException, IOException  {
		CreateTaskForTest();
		
		ArrayList<Task> taskList= to.showTaskList();
		
		Task task4=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.TASK_STATUS, "Done");
		assertEquals("Done", task4.getTaskStatus());
		
		
		
		Task task5 =to.updatetask(taskList.get(1).getTaskDescrptionId(), Constants.TASK_STATUS, "In Progress");
		assertEquals("In Progress", task5.getTaskStatus());
		}
	@Test
	public void testupdateProject() throws ParseException, ClassNotFoundException, IOException  {
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		
		Task task4=to.updatetask(taskList.get(0).getTaskDescrptionId(), Constants.PROJECT_TITLE, "ProjectUpdated");
		assertEquals("ProjectUpdated", task4.getProjectTitle());
		
		
		
		Task task5 =to.updatetask(taskList.get(1).getTaskDescrptionId(), Constants.PROJECT_TITLE, "ProjectUpdated");
		assertEquals("ProjectUpdated", task5.getProjectTitle());
		
		Task task6 =to.updatetask(taskList.get(2).getTaskDescrptionId(), Constants.PROJECT_TITLE, "Project3Updated");
		assertEquals("Project3Updated", task6.getProjectTitle());
		
		}
	@Test
	public void testDeleteTask() throws ParseException, ClassNotFoundException, IOException {

		CreateTaskForTest();
		ArrayList<Task> taskList = to.showTaskList();

		assertEquals(true, to.removeTask(taskList.get(0).getTaskDescrptionId()));

	}
	@Test
	public void testFilterByProject() throws ParseException, ClassNotFoundException, IOException  {
		UpdateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		ArrayList<Task> taskListFilterByProject= to.filterByProject("Project title is updated");
		
		assertEquals(2,taskListFilterByProject.size());
		
		
		
		}
	@Test
	public void testFilterByNullProject() throws ParseException, ClassNotFoundException, IOException  {
		UpdateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		ArrayList<Task> taskListFilterByProject= to.filterByProject(null);
		assertEquals(null,taskListFilterByProject.size());
	}
	
	@Test
	public void testFilterBynotExistingProject() throws ParseException, ClassNotFoundException, IOException  {
		UpdateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		ArrayList<Task> taskListFilterByProject= to.filterByProject(("Not Exists in my task list and is hard coded make sure it does not exists while testing").trim());
		assertEquals(0,taskListFilterByProject.size());
		
	}
	@Test
	public void testSaveWhenNoFileExists() throws ClassNotFoundException, IOException, ParseException  {
		File file = new File("ToDo.csv");
		boolean result = Files.deleteIfExists(file.toPath());
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		assertEquals(true,to.save());
		
	}
	@Test
	public void testSaveWhenEmptyFileExists() throws ClassNotFoundException, IOException, ParseException  {
		File file = new File("ToDo.csv");
		if(file.length()>0) {
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
			System.out.println("File is emptied");
		}
		
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		assertEquals(true,to.save());
		
		
		}
		
	
	@Test
	public void testSaveWhenTaskExistInFile() throws ClassNotFoundException, IOException, ParseException  {
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		to.save();
		sdf.setLenient(false);
		Date dueDate1=sdf.parse("12/12/2018");
		to.createTask("One more TASK", dueDate1, "WIP");
		assertEquals(true,to.save());
		
	}
	@Test
	public void testSaveWhenTaskExistInArrayList() throws ClassNotFoundException, IOException, ParseException  {
		CreateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		assertEquals(true,to.save());
		
	}
	@Test
	public void testSaveWhenNoExistInArrayList() throws ClassNotFoundException, IOException, ParseException  {
		
		to.removeAllTask();
		
		ArrayList<Task> taskList= to.showTaskList();
		assertEquals(true,to.save());
	}
	@Test
	public void testSaveWhenUpdatedTaskExistInArrayList() throws ClassNotFoundException, IOException, ParseException  {
		UpdateTaskForTest();
		ArrayList<Task> taskList= to.showTaskList();
		assertEquals(true,to.save());
	}
}
	

	



