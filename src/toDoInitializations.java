import java.util.ArrayList;
/**
 * This class is responsible for loading all the data required for the application
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */
public class toDoInitializations {
	
	static ArrayList<Task> tasktList = new ArrayList<>();
	
	static Integer count = 0 ;
	
	public ArrayList<Task> loadAllTasks(){
		System.out.println("Here all the pre existing tasks will be loaded");
		count=tasktList.size();
		System.out.println("The size of list is "+count);
		Task task1 = new Task("task1", "12/30/2019", "todo");
		Task task2 = new Task("task2", "12/30/2018", "pending");
		Task task3 = new Task("task3", "12/30/2019", "todo");
		Task task4 = new Task("task4", "12/31/2019", "pending");
		tasktList.add(task1);
		tasktList.add(task2);
		tasktList.add(task3);
		tasktList.add(task4);
		count=tasktList.size();
		System.out.println("After loading the task list the value of size is ::"+count);
		return tasktList;
		
	}

}
