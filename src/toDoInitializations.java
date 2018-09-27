import java.util.ArrayList;
/**
 * This class is responsible for loading all the data required for the application
 * 
 * @author  Swati Gupta
 * @version 2018.09.27
 */
public class toDoInitializations {
	
	ArrayList<Task> tasktList = new ArrayList<>();
	
	public ArrayList<Task> loadAllTasks(){
		Task task1 = new Task("task1", "12/30/2015", "todo");
		Task task2 = new Task("task1", "12/30/2015", "todo");
		Task task3 = new Task("task1", "12/30/2015", "todo");
		Task task4 = new Task("task1", "12/30/2015", "todo");
		tasktList.add(task1);
		tasktList.add(task2);
		tasktList.add(task3);
		tasktList.add(task4);
		return tasktList;
		
	}

}
